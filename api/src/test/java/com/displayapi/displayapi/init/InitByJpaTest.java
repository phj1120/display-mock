package com.displayapi.displayapi.init;

import com.displayapi.displayapi.entity.display.CategoryEntity;
import com.displayapi.displayapi.entity.goods.GoodsEntity;
import com.displayapi.displayapi.repository.display.CategoryRepository;
import com.displayapi.displayapi.repository.goods.GoodsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@SpringBootTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create"
})
public class InitByJpaTest {
    @Autowired
    GoodsRepository goodsRepository;
    @Autowired
    CategoryRepository categoryRepository;

    private final static int GOODS_SIZE = 200000;

    @Rollback(value = false)
    @Test
    void init() {

    }

    @BeforeEach
    void beforeEach() {
        List<CategoryEntity> categoryEntities = categoryRepository.saveAll(List.of(
                new CategoryEntity("가전")
                , new CategoryEntity("의류")
                , new CategoryEntity("식품")
        ));

        List<GoodsEntity> goodsEntities = new ArrayList<>();
        for (long i = 0; i < GOODS_SIZE / 3; i++) {
            goodsEntities.add(makeLumpSumPayGoods(i, categoryEntities));
            goodsEntities.add(makeInstallmentGoods(i, categoryEntities));
            goodsEntities.add(makeInterestFreeGoods(i, categoryEntities));
        }

        goodsRepository.saveAll(goodsEntities);
    }

    private GoodsEntity makeInterestFreeGoods(long i, List<CategoryEntity> categoryEntities) {
        GoodsEntity goods = new GoodsEntity();
        goods.setPrice(i * 100);
        goods.setInstallmentAmount(i * 100 / getInstallmentMonth(i));
        goods.setInstallmentMonth(getInstallmentMonth(i));
        goods.setCategoryEntity(this.getCategoryEntity((int) i, categoryEntities));
        goods.setRank((int) i);

        goods.setInterestFreeGoods(true);

        goods.setDisplay(true);
        goods.setSale(true);
        goods.setDisplayStartDtm(LocalDateTime.now());
        goods.setDisplayEndDtm(LocalDateTime.now());
        goods.setSaleStartDtm(LocalDateTime.now());
        goods.setSaleEndDtm(LocalDateTime.now());

        return goods;
    }

    private GoodsEntity makeInstallmentGoods(long i, List<CategoryEntity> categoryEntities) {
        GoodsEntity goods = new GoodsEntity();
        goods.setPrice(i * 100);
        goods.setInstallmentAmount(i * 100 / getInstallmentMonth(i));
        goods.setInstallmentMonth(getInstallmentMonth(i));
        goods.setCategoryEntity(this.getCategoryEntity((int) i, categoryEntities));
        goods.setRank((int) i);

        goods.setInstallmentGoods(true);

        goods.setDisplay(true);
        goods.setSale(true);
        goods.setDisplayStartDtm(LocalDateTime.now());
        goods.setDisplayEndDtm(LocalDateTime.now());
        goods.setSaleStartDtm(LocalDateTime.now());
        goods.setSaleEndDtm(LocalDateTime.now());

        return goods;
    }

    private GoodsEntity makeLumpSumPayGoods(long i, List<CategoryEntity> categoryEntities) {
        GoodsEntity goods = new GoodsEntity();
        goods.setPrice(i * 100);
        goods.setCategoryEntity(this.getCategoryEntity((int) i, categoryEntities));
        goods.setRank((int) i);

        goods.setLumpSumPayGoods(true);

        goods.setDisplay(true);
        goods.setSale(true);
        goods.setDisplayStartDtm(LocalDateTime.now());
        goods.setDisplayEndDtm(LocalDateTime.now());
        goods.setSaleStartDtm(LocalDateTime.now());
        goods.setSaleEndDtm(LocalDateTime.now());

        return goods;
    }

    CategoryEntity getCategoryEntity(int index, List<CategoryEntity> categoryEntities) {
        return categoryEntities.get((index % categoryEntities.size()));
    }

    int getInstallmentMonth(long index) {
        List<Integer> allInstallment = List.of(3, 6, 9, 12);
        return allInstallment.get((int) (index % allInstallment.size()));
    }
}

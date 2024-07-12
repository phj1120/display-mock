package com.displayapi.displayapi.repository.goods;

import com.displayapi.displayapi.entity.goods.GoodsEntity;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@Transactional
@SpringBootTest
class GoodsRepositoryTest {
    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    void getDisplayableGoods() {
        GoodsEntity goodsEntity = new GoodsEntity();

        List<GoodsEntity> displayableGoods = goodsRepository.getDisplayableGoods();

        log.info("FINISH");
    }
}
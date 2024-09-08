package com.displayapi.displayapi.repository.goods;

import com.displayapi.displayapi.dto.goods.Goods;
import com.querydsl.core.types.Projections;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.displayapi.displayapi.entity.display.QCategoryEntity.categoryEntity;
import static com.displayapi.displayapi.entity.goods.QGoodsEntity.goodsEntity;

// 테스트대상_시나리오_예상결과
@Slf4j
@SpringBootTest
class GoodsRepositoryCustomImplTest {
    @Autowired
    private GoodsQueryFactory goodsQueryFactory;

    @Test
    void goods_CompareIndividualAndCommonMethod_ResultIsEquivalent() {
        List<Goods> goodsList1 = goodsQueryFactory
                .selectFromGoods(Projections.fields(Goods.class,
                        goodsEntity.goodsNo,
                        goodsEntity.price))
                .fetch();
        List<Goods> goodsList2 = goodsQueryFactory
                .selectFromGoods()
                .fetch();

        Assertions.assertThat(goodsList1).isEqualTo(goodsList2);
    }

    @Test
    void superDealGoods_CompareIndividualAndCommonMethod_ResultIsEquivalent() {
        List<Goods> superDealGoodsList1 = goodsQueryFactory
                .selectFromSuperDeal(Projections.fields(Goods.class,
                        goodsEntity.goodsNo,
                        goodsEntity.price,
                        categoryEntity.categoryNo))
                .fetch();
        List<Goods> superDealGoodsList2 = goodsQueryFactory
                .selectFromSuperDeal()
                .fetch();

        Assertions.assertThat(superDealGoodsList1).isEqualTo(superDealGoodsList2);
    }

    @Test
    void goods_CompareListSizeAndCount_ResultIsEquivalent() {
        Long count = goodsQueryFactory.selectFromGoods(goodsEntity.goodsNo.count()).fetchOne();
        long listCount = goodsQueryFactory.selectFromGoods().fetch().size();

        Assertions.assertThat(count).isEqualTo(listCount);
    }

    @Test
    void superDealGoods_CompareListSizeAndCount_ResultIsEquivalent() {
        Long count = goodsQueryFactory.selectFromSuperDeal(goodsEntity.goodsNo.count()).fetchOne();
        long listCount = goodsQueryFactory.selectFromSuperDeal().fetch().size();

        Assertions.assertThat(count).isEqualTo(listCount);
    }


    @Test
    void jpaQueryMethodOrder_MixedOrder_SuccessfulQuerying() {
        List<Goods> goodsList = goodsQueryFactory.select(Projections.fields(Goods.class,
                        goodsEntity.goodsNo,
                        goodsEntity.price))
                .where(goodsEntity.isDisplay.isTrue())
                .where(goodsEntity.isInstallmentGoods.isTrue())
                .from(goodsEntity)
                .innerJoin(goodsEntity.categoryEntity, categoryEntity)
                .fetch();

        Assertions.assertThat(goodsList).isNotEmpty();
    }
}
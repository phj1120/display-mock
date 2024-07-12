package com.displayapi.displayapi.repository.goods;

import com.displayapi.displayapi.entity.goods.GoodsEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.displayapi.displayapi.entity.goods.QGoodsEntity.goodsEntity;

@RequiredArgsConstructor
public class GoodsRepositoryCustomImpl implements GoodsRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<GoodsEntity> getDisplayableGoods() {
        return queryFactory.select(goodsEntity)
                .from(goodsEntity)
                .where(goodsEntity.isDisplay.isTrue())
                .fetch();
    }
}

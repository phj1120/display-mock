package com.displayapi.displayapi.repository.goods;

import com.displayapi.displayapi.dto.goods.Goods;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.displayapi.displayapi.entity.display.QCategoryEntity.categoryEntity;
import static com.displayapi.displayapi.entity.goods.QGoodsEntity.goodsEntity;

@Component
@RequiredArgsConstructor
public class GoodsQueryFactory {
    private final JPAQueryFactory queryFactory;

    public <T> JPAQuery<T> select(Expression<T> expression) {
        return queryFactory.select(expression);
    }

    private final List<Expression<?>> GOODS_COMMON_EXPRESSIONS = List.of(goodsEntity.goodsNo, goodsEntity.price);
    private final List<Expression<?>> SUPER_DEAL_EXPRESSIONS = List.of(categoryEntity.categoryNo);

    private Expression<Goods> createGoodsExpression(List<Expression<?>> expressions) {
        return Projections.fields(Goods.class, expressions.toArray(new Expression[0]));
    }

    private Expression<Goods> goodsExpression(){
        return createGoodsExpression(GOODS_COMMON_EXPRESSIONS);
    }

    private Expression<Goods> goodsSuperDealExpression() {
        List<Expression<?>> superDealExpressions = new ArrayList<>();
        superDealExpressions.addAll(GOODS_COMMON_EXPRESSIONS);
        superDealExpressions.addAll(SUPER_DEAL_EXPRESSIONS);

        return createGoodsExpression(superDealExpressions);
    }

    public <T> JPAQuery<T> selectFromGoods(Expression<T> expression) {
        return select(expression)
                // 공통 From
                .from(goodsEntity)
                ;
    }

    public JPAQuery<Goods> selectFromGoods() {
        return selectFromGoods(goodsExpression());
    }

    public <T> JPAQuery<T> selectFromSuperDeal(Expression<T> expression) {
        return selectFromGoods(expression)
                // 슈퍼딜 From
                .leftJoin(goodsEntity.categoryEntity, categoryEntity)
                ;
    }

    public JPAQuery<Goods> selectFromSuperDeal() {
        return selectFromSuperDeal(goodsSuperDealExpression());
    }
}

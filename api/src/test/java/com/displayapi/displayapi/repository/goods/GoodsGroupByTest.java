package com.displayapi.displayapi.repository.goods;

import com.displayapi.displayapi.dto.goods.GoodsCountDto;
import com.displayapi.displayapi.dto.goods.GoodsTab;
import com.displayapi.displayapi.dto.goods.GoodsTabSearch;
import com.displayapi.displayapi.dto.goods.TabSearchType;
import com.displayapi.displayapi.entity.goods.GoodsEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static com.displayapi.displayapi.entity.goods.QGoodsEntity.goodsEntity;

@Slf4j
@SpringBootTest
class GoodsGroupByTest {
    @Autowired
    private JPAQueryFactory queryFactory;

    @Test
    void getDisplayableGoods() {
        List<GoodsEntity> goods = queryFactory.select(goodsEntity)
                .from(goodsEntity)
                .where(goodsEntity.isDisplay.isTrue())
                .fetch();
        log.info("goods: {}", goods);
    }

    @Test
    void groupBy() {
        List<GoodsCountDto> goodsCountDtos = queryFactory.select(Projections.fields(GoodsCountDto.class,
                        goodsEntity.goodsNo,
                        ExpressionUtils.as(goodsEntity.goodsNo.count(), "count")
                )).from(goodsEntity)
                .where(goodsEntity.isDisplay)
                .groupBy(goodsEntity.goodsNo)
                .fetch();
        log.info("goodsCountDtos: {}", goodsCountDtos);
    }

    @Test
    void groupByWithCase() {
        List<GoodsCountDto> goodsCountDtos = queryFactory.select(Projections.fields(GoodsCountDto.class,
                        goodsEntity.goodsNo,
                        new CaseBuilder()
                                .when(goodsEntity.isInstallmentGoods).then("INSTALLMENT")
                                .when(goodsEntity.isInterestFreeGoods).then("INTERESTFREE")
                                .when(goodsEntity.isLumpSumPayGoods).then("LUMPSUMPAY")
                                .otherwise("OTHER").as("tabId"),
                        ExpressionUtils.as(goodsEntity.goodsNo.count(), "count")
                )).from(goodsEntity)
                .where(goodsEntity.isDisplay)
                .groupBy(goodsEntity.goodsNo, new CaseBuilder()
                        .when(goodsEntity.isInstallmentGoods).then("INSTALLMENT")
                        .when(goodsEntity.isInterestFreeGoods).then("INTERESTFREE")
                        .when(goodsEntity.isLumpSumPayGoods).then("LUMPSUMPAY")
                        .otherwise("OTHER")
                )
                .fetch();

        log.info("goodsCountDtos: {}", goodsCountDtos);
    }

    @Test
    void groupByWithCaseLumpSumPayGoodsTab() {
        // 일시불: 카테고리, 가격 범위
        StringExpression caseBuilder = new CaseBuilder()
                .when(goodsEntity.categoryEntity.categoryNo.eq(1L).and(goodsEntity.price.between(0L, 1000000L))).then("1-1")
                .when(goodsEntity.categoryEntity.categoryNo.eq(1L).and(goodsEntity.price.between(1000000L, 2000000L))).then("1-2")
                .when(goodsEntity.categoryEntity.categoryNo.eq(1L).and(goodsEntity.price.goe(2000000L))).then("1-3")
                .otherwise("null");
        List<GoodsCountDto> goodsCountDtos = queryFactory.select(Projections.fields(GoodsCountDto.class,
                        caseBuilder.as("tabId"),
                        goodsEntity.count().as("count")
                )).from(goodsEntity)
                .where(goodsEntity.isDisplay, goodsEntity.isLumpSumPayGoods, goodsEntity.categoryEntity.categoryNo.eq(1L))
                .groupBy(
                        caseBuilder
                )
                .fetch();

        log.info("goodsCountDtos: {}", goodsCountDtos);
    }

    @Test
    void subQueryAtFrom() {

        StringExpression caseBuilder = new CaseBuilder()
                .when(goodsEntity.categoryEntity.categoryNo.eq(1L).and(goodsEntity.price.between(0L, 1000000L))).then("1-1")
                .when(goodsEntity.categoryEntity.categoryNo.eq(1L).and(goodsEntity.price.between(1000000L, 2000000L))).then("1-2")
                .when(goodsEntity.categoryEntity.categoryNo.eq(1L).and(goodsEntity.price.goe(2000000L))).then("1-3")
                .otherwise("null");
        List<GoodsCountDto> goodsCountDtos = queryFactory.select(Projections.fields(GoodsCountDto.class,
                        caseBuilder.as("tabId"),
                        goodsEntity.count().as("count")
                )).from(goodsEntity)
                .where(goodsEntity.isDisplay, goodsEntity.isLumpSumPayGoods, goodsEntity.categoryEntity.categoryNo.eq(1L))
                .groupBy(
                        caseBuilder
                )
                .fetch();

        log.info("goodsCountDtos: {}", goodsCountDtos);
    }

    // JPA 유지 하고 N 번 쿼리 날릴지, Mybatis 추가하고 원쿼리로 해결할지 고민 필요
    @Test
    void getTabProductCountsMultipleQuery() {
        GoodsTabSearch goodsTabSearch = new GoodsTabSearch();
        goodsTabSearch.setTabSearchType(TabSearchType.LUMP_SUM_PAY_GOODS.getCode());
        goodsTabSearch.setGoodsTabList(List.of(
                GoodsTab.of("1", 1L, null, null, null),
                GoodsTab.of("1-1", 1L, 0L, 1000000L, null),
                GoodsTab.of("1-2", 1L, 1000000L, 2000000L, null),
                GoodsTab.of("1-3", 1L, 2000000L, null, null),

                GoodsTab.of("2", 2L, null, null, null),
                GoodsTab.of("2-1", 2L, 0L, 1000000L, null),
                GoodsTab.of("2-2", 2L, 1000000L, 2000000L, null),
                GoodsTab.of("2-3", 2L, 2000000L, null, null),

                GoodsTab.of("3", 3L, null, null, null),
                GoodsTab.of("3-1", 3L, 0L, 1000000L, null),
                GoodsTab.of("3-2", 3L, 1000000L, 2000000L, null),
                GoodsTab.of("3-3", 3L, 2000000L, null, null)
        ));

        // 동적 쿼리 빌더
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(goodsEntity.isDisplay.isTrue())
                .and(goodsEntity.isLumpSumPayGoods.isTrue());

        // 결과 리스트
        List<GoodsCountDto> goodsCountDtos = goodsTabSearch.getGoodsTabList().stream()
                .flatMap(condition -> {
                    BooleanBuilder conditionBuilder = new BooleanBuilder(builder);
                    conditionBuilder.and(getCategoryNo(condition.getCategoryNo()));
                    conditionBuilder.and(getPriceCondition(condition.getMinPrice(), condition.getMaxPrice()));
                    conditionBuilder.and(getInstallmentCondition(condition.getInstallmentMonth()));

                    return queryFactory
                            .select(Projections.constructor(GoodsCountDto.class,
                                    ExpressionUtils.as(Expressions.constant(condition.getTabId()), "tabId"),
                                    goodsEntity.count().as("count")
                            ))
                            .from(goodsEntity)
                            .where(conditionBuilder)
                            .fetch()
                            .stream();
                })
                .collect(Collectors.toList());

        log.info("finish: {}", goodsCountDtos);
    }

    @Test
    void getTabProductCountsSingleQuery() {
        GoodsTabSearch goodsTabSearch = new GoodsTabSearch();
        goodsTabSearch.setTabSearchType(TabSearchType.LUMP_SUM_PAY_GOODS.getCode());
        goodsTabSearch.setGoodsTabList(List.of(
                GoodsTab.of("1-1", 1L, 0L, 1000000L, null),
                GoodsTab.of("1-2", 1L, 1000000L, 2000000L, null),
                GoodsTab.of("1-3", 1L, 2000000L, null, null),

                GoodsTab.of("2-1", 2L, 0L, 1000000L, null),
                GoodsTab.of("2-2", 2L, 1000000L, 2000000L, null),
                GoodsTab.of("2-3", 2L, 2000000L, null, null),

                GoodsTab.of("3-1", 3L, 0L, 1000000L, null),
                GoodsTab.of("3-2", 3L, 1000000L, 2000000L, null),
                GoodsTab.of("3-3", 3L, 2000000L, null, null)
        ));

        List<GoodsTab> tabConditions = goodsTabSearch.getGoodsTabList();
        BooleanExpression trueExpression = goodsEntity.goodsNo.eq(goodsEntity.goodsNo);
        CaseBuilder.Cases<String, StringExpression> caseExpression = null;
        for (GoodsTab condition : tabConditions) {
            BooleanExpression tabCondition = trueExpression;

            BooleanExpression categoryCondition = getCategoryNo(condition.getCategoryNo());
            if (categoryCondition != null) {
                tabCondition = tabCondition.and(categoryCondition);
            }

            BooleanExpression priceCondition = getPriceCondition(condition.getMinPrice(), condition.getMaxPrice());
            if (priceCondition != null) {
                tabCondition = tabCondition.and(priceCondition);
            }

            BooleanExpression installmentCondition = getInstallmentCondition(condition.getInstallmentMonth());
            if (installmentCondition != null) {
                tabCondition = tabCondition.and(installmentCondition);
            }

            if (caseExpression == null) {
                caseExpression = new CaseBuilder().when(tabCondition).then(condition.getTabId());
            } else {
                caseExpression = caseExpression.when(tabCondition).then(condition.getTabId());
            }
        }
        if (caseExpression == null) {
            caseExpression = new CaseBuilder().when(trueExpression).then("default");
        }

        StringExpression caseTabIcd = caseExpression.otherwise("none");

        BooleanExpression baseCondition = goodsEntity.isDisplay.isTrue()
                .and(goodsEntity.isLumpSumPayGoods.isTrue());
        List<GoodsCountDto> goodsCountDtos = queryFactory
                .select(Projections.fields(GoodsCountDto.class,
                        caseTabIcd.as("tabId"),
                        goodsEntity.count().as("count"))) // count 추가
                .from(goodsEntity)
                .where(baseCondition)
                .groupBy(caseTabIcd)
                .fetch();

        log.info("finish: {}", goodsCountDtos);
    }

    private BooleanExpression getCategoryNo(Long categoryNo) {
        if (categoryNo != null) {
            return goodsEntity.categoryEntity.categoryNo.eq(categoryNo);
        }
        return null;
    }

    private BooleanExpression getPriceCondition(Long minPrice, Long maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return null;
        }
        if (minPrice != null && maxPrice != null) {
            return goodsEntity.price.between(minPrice, maxPrice);
        }
        if (minPrice != null) {
            return goodsEntity.price.goe(minPrice);
        }
        return goodsEntity.price.loe(maxPrice);
    }

    private BooleanExpression getInstallmentCondition(Integer installmentMonths) {
        if (installmentMonths == null) {
            return null;
        }
        return goodsEntity.installmentMonth.eq(installmentMonths);
    }


    // QueryDsl Union All 지원하지 않음.
//    public void getTabProductCountsOneQuery() {
//        GoodsTabSearch goodsTabSearch = makeGoodsTabSearch();
//
//        JPAQuery<GoodsCountDto> query = null;
//        for (GoodsTab condition : goodsTabSearch.getGoodsTabList()) {
//            JPAQuery<GoodsCountDto> subQuery = queryFactory
//                    .select(Projections.fields(GoodsCountDto.class,
//                            ExpressionUtils.as(Expressions.constant(condition.getTabId()), "tabId"),
//                            goodsEntity.count().as("count")
//                    ))
//                    .from(goodsEntity)
//                    .where(goodsEntity.isDisplay.isTrue()
//                            .and(goodsEntity.isLumpSumPayGoods.isTrue())
//                            .and(goodsEntity.categoryEntity.categoryNo.eq(condition.getCategoryNo()))
//                            .and(getCategoryNo(condition.getCategoryNo()))
//                            .and(getPriceCondition(condition.getMinPrice(), condition.getMaxPrice()))
//                            .and(getInstallmentCondition(condition.getInstallmentMonth())));
//            if (query == null) {
//                query = subQuery;
//            } else {
//                query = query.unionAll(subQuery);
//            }
//        }
//
//        return query.fetch();
//    }
}

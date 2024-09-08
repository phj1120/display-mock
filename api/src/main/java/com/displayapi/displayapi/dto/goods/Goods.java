package com.displayapi.displayapi.dto.goods;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Goods {
    private Long goodsNo; // 상품 번호

    private Long price; // 가격

    private Long installmentAmount; // 할부금액

    private Integer installmentMonth; // 할부개월

    private Long categoryNo; // 카테고리 번호

    private int rank; // 순위

    private boolean isDisplay; // 전시중

    private boolean isSale; // 판매중

    private LocalDateTime displayStartDtm; // 전시시작일시

    private LocalDateTime displayEndDtm; // 전시종료일시

    private LocalDateTime saleStartDtm; // 판매시작일시

    private LocalDateTime saleEndDtm; // 판매종료일시

    private boolean isInterestFreeGoods; // 무이자상품여부

    private boolean isInstallmentGoods; // 할부상품여부

    private boolean isLumpSumPayGoods; // 일시불상품여부
}

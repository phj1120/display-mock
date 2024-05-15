package com.displayapi.displayapi.enums.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PromotionType {
    GOODS("10", "상품쿠폰"),
    BASKET("20", "장바구니쿠폰");
    private String code;
    private String codeName;
}

package com.displayapi.displayapi.enums.promotion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountType {
    AMT("10", "정액"),
    RATE("20", "정률");

    private String code;
    private String codeName;
}

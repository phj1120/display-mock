package com.displayapi.displayapi.dto.goods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum TabSearchType {
    INTEREST_FREE_GOODS("10", "무이자"),
    INSTALLMENT_GOODS("20", "할부"),
    LUMP_SUM_PAY_GOODS("30", "일시불"),
    ;

    private String code;
    private String codeName;

    public static TabSearchType getByCode(String code) {
        for (TabSearchType tabSearchType : TabSearchType.values()) {
            if (tabSearchType.getCode().equals(code)) {
                return tabSearchType;
            }
        }
        return null;
    }

    public boolean isEquals(String code) {
        return this.code.equals(code);
    }
}

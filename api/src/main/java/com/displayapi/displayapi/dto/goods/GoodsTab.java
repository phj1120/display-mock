package com.displayapi.displayapi.dto.goods;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodsTab {
    private String tabId;

    private Long categoryNo;

    private Long minPrice;
    private Long maxPrice;

    private Integer installmentMonth;

    private int count;

    public static GoodsTab of(String tabId, Long categoryNo, Long minPrice, Long maxPrice, Integer installmentMonth) {
        GoodsTab goodsTab = new GoodsTab();
        goodsTab.setTabId(tabId);
        goodsTab.setCategoryNo(categoryNo);
        goodsTab.setMinPrice(minPrice);
        goodsTab.setMaxPrice(maxPrice);
        goodsTab.setInstallmentMonth(installmentMonth);

        return goodsTab;
    }
}

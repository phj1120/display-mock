package com.displayapi.displayapi.dto.goods;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class GoodsTabSearch {
    private String tabSearchType;

    private List<GoodsTab> goodsTabList = new ArrayList<>();
}

package com.displayapi.displayapi.dto.display;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class ShopDto {
    private Long shopNo;
    private String shopNm;
//    private TemplateDto templateDto;
//    private List<CornerDto> corners = new ArrayList<>();

    @QueryProjection
    public ShopDto(Long shopNo, String shopNm) {
        this.shopNo = shopNo;
        this.shopNm = shopNm;
//        this.templateDto = templateDto;
//        this.corners = corners;
    }
}

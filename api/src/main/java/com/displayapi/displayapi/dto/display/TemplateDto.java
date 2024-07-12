package com.displayapi.displayapi.dto.display;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class TemplateDto {
    private Long templateNo;
    private String templateId;
    private String templateName;

    @QueryProjection
    public TemplateDto(Long templateNo, String templateId, String templateName) {
        this.templateName = templateName;
        this.templateId = templateId;
        this.templateNo = templateNo;
    }
}

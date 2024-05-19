package com.displayapi.displayapi.entity.display;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShopTemplateEntity {
    @Id
    @GeneratedValue
    private Long shopTemplateNo;

    private String shopTemplateName;

    @ManyToOne(fetch = LAZY)
    private ShopEntity shop;

    @ManyToOne(fetch = LAZY)
    private TemplateEntity template;

}

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
@Table(name = "shop_template")
@SequenceGenerator(
        name = "SHOP_TEMPLATE_GENERATOR",
        sequenceName = "shop_template_seq01",
        initialValue = 1,
        allocationSize = 1
)
public class ShopTemplateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOP_TEMPLATE_GENERATOR")
    private Long shopTemplateNo;

    private String shopTemplateName;

    @ManyToOne(fetch = LAZY)
    private ShopEntity shop;

    @ManyToOne(fetch = LAZY)
    private TemplateEntity template;

}

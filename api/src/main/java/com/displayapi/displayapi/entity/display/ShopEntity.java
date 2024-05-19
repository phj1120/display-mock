package com.displayapi.displayapi.entity.display;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShopEntity {
    @Id
    @GeneratedValue
    private Long shopNo;

    private String shopName;

    // TODO 전시카테고리, 브랜드 추가.

    @OneToMany(mappedBy = "shop", fetch = LAZY)
    private List<ShopTemplateEntity> shopTemplateList;
}

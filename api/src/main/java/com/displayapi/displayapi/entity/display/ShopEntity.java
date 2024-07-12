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
@Table(name = "shop")
@SequenceGenerator(
        name = "SHOP_GENERATOR",
        sequenceName = "shop_seq01",
        initialValue = 1,
        allocationSize = 1
)
public class ShopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SHOP_GENERATOR")
    private Long shopNo;

    private String shopName;

    // TODO 전시카테고리, 브랜드 추가.
}

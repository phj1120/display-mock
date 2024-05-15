package com.displayapi.displayapi.entity.promotion;

import com.displayapi.displayapi.enums.promotion.DiscountType;
import com.displayapi.displayapi.enums.promotion.PromotionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class PromotionEntity {
    @Id
    @GeneratedValue
    private Long promotionNo;

    private String name;

    private int validateDays;

    @Enumerated(EnumType.STRING)
    private PromotionType promotionType;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    private long discount;

    @OneToMany(mappedBy = "issueNo", fetch = FetchType.LAZY)
    private List<IssuedMemberEntity> issuedList = new ArrayList<>();

    public PromotionEntity(String name, int validateDays, PromotionType promotionType, DiscountType discountType, long discount) {
        this.name = name;
        this.validateDays = validateDays;
        this.promotionType = promotionType;
        this.discountType = discountType;
        this.discount = discount;
    }
}

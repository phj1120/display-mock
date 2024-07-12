package com.displayapi.displayapi.entity.promotion;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class IssuedMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long issueNo;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private PromotionEntity promotion;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime issuedDate;
    private Boolean isUsed;
    private LocalDateTime useDtm;
    private String ordNo;


    public IssuedMemberEntity(PromotionEntity promotion) {
        LocalDateTime now = LocalDateTime.now();
        this.promotion = promotion;
        this.issuedDate = now;
        this.startDate = now;
        this.endDate = now.plusDays(promotion.getValidateDays());
        this.isUsed = false;
    }
}

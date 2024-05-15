package com.displayapi.displayapi.promotion;

import com.displayapi.displayapi.entity.promotion.IssuedMemberEntity;
import com.displayapi.displayapi.entity.promotion.PromotionEntity;
import com.displayapi.displayapi.enums.promotion.DiscountType;
import com.displayapi.displayapi.enums.promotion.PromotionType;
import com.displayapi.displayapi.repository.promotion.IssuedMemberRepository;
import com.displayapi.displayapi.repository.promotion.PromotionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest
public class PromotionRepositoryTests {
    @Autowired
    IssuedMemberRepository issuedMemberRepository;
    @Autowired
    PromotionRepository promotionRepository;

    @BeforeEach
    void init() {
        PromotionEntity promotionEntity = new PromotionEntity(
                "쿠폰1",
                1,
                PromotionType.GOODS,
                DiscountType.AMT,
                1000
        );
        promotionRepository.save(promotionEntity);

        IssuedMemberEntity issuedMemberEntity1 = new IssuedMemberEntity(promotionEntity);
        IssuedMemberEntity issuedMemberEntity2 = new IssuedMemberEntity(promotionEntity);
        IssuedMemberEntity issuedMemberEntity3 = new IssuedMemberEntity(promotionEntity);
        issuedMemberRepository.save(issuedMemberEntity1);
        issuedMemberRepository.save(issuedMemberEntity2);
        issuedMemberRepository.save(issuedMemberEntity3);

        PromotionEntity promotionEntity2 = new PromotionEntity(
                "쿠폰2",
                7,
                PromotionType.BASKET,
                DiscountType.RATE,
                10
        );
        promotionRepository.save(promotionEntity);

        IssuedMemberEntity issuedMemberEntity4 = new IssuedMemberEntity(promotionEntity2);
        IssuedMemberEntity issuedMemberEntity5 = new IssuedMemberEntity(promotionEntity2);
        IssuedMemberEntity issuedMemberEntity6 = new IssuedMemberEntity(promotionEntity2);
        issuedMemberRepository.save(issuedMemberEntity4);
        issuedMemberRepository.save(issuedMemberEntity5);
        issuedMemberRepository.save(issuedMemberEntity6);
    }

    @Test
    void test() {
        Optional<PromotionEntity> coupon1 = promotionRepository.findByName("쿠폰1");
        Assertions.assertThat(coupon1).isPresent();
    }
}

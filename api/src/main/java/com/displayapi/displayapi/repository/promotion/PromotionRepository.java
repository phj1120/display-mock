package com.displayapi.displayapi.repository.promotion;

import com.displayapi.displayapi.entity.promotion.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromotionRepository extends JpaRepository<PromotionEntity, Long> {
    Optional<PromotionEntity> findByName(String name);
}

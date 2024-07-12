package com.displayapi.displayapi.repository.goods;

import com.displayapi.displayapi.entity.goods.GoodsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<GoodsEntity, Long>, GoodsRepositoryCustom {
}

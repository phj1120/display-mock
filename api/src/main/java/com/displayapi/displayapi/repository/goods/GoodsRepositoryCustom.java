package com.displayapi.displayapi.repository.goods;

import com.displayapi.displayapi.entity.goods.GoodsEntity;

import java.util.List;

public interface GoodsRepositoryCustom {
    List<GoodsEntity> getDisplayableGoods();
}

package com.displayapi.displayapi.repository.display;

import com.displayapi.displayapi.entity.display.CornerEntity;

public interface CornerRepositoryCustom {
    CornerEntity findByCornerName(String cornerName);

}

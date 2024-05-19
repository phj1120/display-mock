package com.displayapi.displayapi.repository.display;

import com.displayapi.displayapi.entity.display.CornerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CornerRepository extends JpaRepository<CornerEntity, Long>, CornerRepositoryCustom {

}

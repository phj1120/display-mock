package com.displayapi.displayapi.repository.display;

import com.displayapi.displayapi.entity.display.CornerEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CornerRepositoryTest {
    @Autowired
    CornerRepository cornerRepository;

    @Autowired
    EntityManager em;

    @Test
    void settingCornerRepositoryWithCornerRepositoryCustom() {
        CornerEntity cornerEntity = new CornerEntity();
        cornerEntity.setCornerName("코너1");

        cornerRepository.save(cornerEntity);

        em.flush();
        em.clear();

        CornerEntity findByCornerName = cornerRepository.findByCornerName("코너1");
        Optional<CornerEntity> findByCornerNo = cornerRepository.findById(findByCornerName.getCornerNo());

        assertThat(findByCornerNo.get()).isEqualTo(findByCornerName);
    }
}
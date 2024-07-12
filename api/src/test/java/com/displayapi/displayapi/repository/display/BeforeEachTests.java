package com.displayapi.displayapi.repository.display;

import com.displayapi.displayapi.entity.display.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@Transactional
public class BeforeEachTests {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopTemplateRepository shopTemplateRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private TemplateCornerRepository templateCornerRepository;
    @Autowired
    private CornerRepository cornerRepository;

    @BeforeEach
    void beforeEach() {
        // 매장 등록
        ShopEntity shopEntity1 = new ShopEntity();
        shopEntity1.setShopName("매장1");
        shopRepository.save(shopEntity1);

        // 코너 등록
        CornerEntity cornerEntity1 = new CornerEntity();
        cornerEntity1.setCornerName("코너1");
        cornerRepository.save(cornerEntity1);

        CornerEntity cornerEntity2 = new CornerEntity();
        cornerEntity2.setCornerName("코너2");
        cornerRepository.save(cornerEntity2);

        // 템플릿1
        // 등록
        TemplateEntity templateEntity1 = new TemplateEntity();
        templateEntity1.setTemplateName("템플릿1");
        templateRepository.save(templateEntity1);

        // 매장과 템플릿 매핑
        ShopTemplateEntity shopTemplateEntity1 = new ShopTemplateEntity();
        shopTemplateEntity1.setShopTemplateName("매장1 - 템플릿1");
        shopTemplateEntity1.setTemplate(templateEntity1);
        shopTemplateEntity1.setShop(shopEntity1);
        shopTemplateRepository.save(shopTemplateEntity1);

        // 템플릿과 코너 매핑
        TemplateCornerEntity templateCornerEntity1 = new TemplateCornerEntity();
        templateCornerEntity1.setTemplateCornerName("템플릿1 - 코너1");
        templateCornerEntity1.setTemplate(templateEntity1);
        templateCornerEntity1.setCorner(cornerEntity1);
        templateCornerRepository.save(templateCornerEntity1);

        // 템플릿 2
        // 등록
        TemplateEntity templateEntity2 = new TemplateEntity();
        templateEntity2.setTemplateName("템플릿2");
        templateRepository.save(templateEntity2);

        // 매장과 템플릿 매핑
        ShopTemplateEntity shopTemplateEntity2 = new ShopTemplateEntity();
        shopTemplateEntity2.setShopTemplateName("매장1 - 템플릿2");
        shopTemplateEntity2.setTemplate(templateEntity2);
        shopTemplateEntity2.setShop(shopEntity1);
        shopTemplateRepository.save(shopTemplateEntity2);

        // 템플릿과 코너 매핑
        TemplateCornerEntity templateCornerEntity2 = new TemplateCornerEntity();
        templateCornerEntity2.setTemplateCornerName("템플릿2 - 코너1");
        templateCornerEntity2.setTemplate(templateEntity2);
        templateCornerEntity2.setCorner(cornerEntity1);
        templateCornerRepository.save(templateCornerEntity2);

        TemplateCornerEntity templateCornerEntity3 = new TemplateCornerEntity();
        templateCornerEntity3.setTemplateCornerName("템플릿2 - 코너2");
        templateCornerEntity3.setTemplate(templateEntity2);
        templateCornerEntity3.setCorner(cornerEntity2);
        templateCornerRepository.save(templateCornerEntity3);

        entityManager.flush();
        entityManager.clear();
    }

    @Test
    void test() {
        log.info("FINISH");
    }
}

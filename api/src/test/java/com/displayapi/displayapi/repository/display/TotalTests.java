package com.displayapi.displayapi.repository.display;

import com.displayapi.displayapi.entity.display.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.displayapi.displayapi.entity.display.QCornerEntity.cornerEntity;
import static com.displayapi.displayapi.entity.display.QShopEntity.shopEntity;
import static com.displayapi.displayapi.entity.display.QShopTemplateEntity.shopTemplateEntity;
import static com.displayapi.displayapi.entity.display.QTemplateCornerEntity.templateCornerEntity;
import static com.displayapi.displayapi.entity.display.QTemplateEntity.templateEntity;

@Slf4j
@Transactional
@SpringBootTest
public class TotalTests {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private JPAQueryFactory queryFactory;
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

        // 템플릿 등록
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

        TemplateCornerEntity templateCornerEntity2 = new TemplateCornerEntity();
        templateCornerEntity2.setTemplateCornerName("템플릿1 - 코너2");
        templateCornerEntity2.setTemplate(templateEntity1);
        templateCornerEntity2.setCorner(cornerEntity2);
        templateCornerRepository.save(templateCornerEntity2);

        entityManager.flush();
        entityManager.clear();
    }


    /**
     * select *
     * from SHOP_ENTITY se
     *     left join SHOP_TEMPLATE_ENTITY ste on se.SHOP_NO = ste.SHOP_SHOP_NO -- 가장 최근거 하나만 가져와야 되는데..
     *     left join TEMPLATE_ENTITY te on te.TEMPLATE_NO = ste.TEMPLATE_TEMPLATE_NO
     *     left join TEMPLATE_CORNER_ENTITY tce on tce.TEMPLATE_TEMPLATE_NO = te.TEMPLATE_NO
     *     left join CORNER_ENTITY ce on ce.CORNER_NO = tce.CORNER_CORNER_NO
     * where SHOP_NO = #{shopNo}
     */
    @Test
    void test() {
        long shopNo = 1L;
//        List<ShopEntity> shopEntities = queryFactory
//                .select(shopEntity)
//                .from(shopEntity)
//                .leftJoin(shopEntity.shopTemplateList, shopTemplateEntity)
//                .leftJoin(shopTemplateEntity.template, templateEntity)
//                .leftJoin(templateEntity.cornerList, templateCornerEntity)
//                .leftJoin(templateCornerEntity.corner, cornerEntity)
//                .where(shopEntity.shopNo.eq(shopNo))
//                .fetch();

        // 반대 순서로 가보려 했는데, 어려움. on 으로 도전
//        List<ShopEntity> shopEntities = queryFactory
//                .select(shopEntity)
//                .from(shopTemplateEntity)
//                .leftJoin(shopTemplateEntity.shop, shopEntity)
//                .leftJoin(shopTemplateEntity.template, templateEntity)
//                .leftJoin(templateCornerEntity.template, templateEntity)
////                .leftJoin(templateEntity.cornerList, templateCornerEntity)
////                .leftJoin(templateCornerEntity.corner, cornerEntity)
//                .where(shopEntity.shopNo.eq(shopNo))
//                .fetch();

//         쿼리는 원하는대로 나가는데, 정보 못가져옴.
//        List<ShopEntity> shopEntities = queryFactory
//                .select(shopEntity)
//                .from(shopEntity)
//                .leftJoin(shopTemplateEntity).on(shopEntity.shopNo.eq(shopTemplateEntity.shop.shopNo)).fetchJoin()
//                .leftJoin(templateEntity).on(templateEntity.templateNo.eq(shopTemplateEntity.template.templateNo)).fetchJoin()
//                .leftJoin(templateCornerEntity).on(templateCornerEntity.template.templateNo.eq(templateEntity.templateNo)).fetchJoin()
//                .leftJoin(cornerEntity).on(cornerEntity.cornerNo.eq(templateCornerEntity.corner.cornerNo)).fetchJoin()
//                .where(shopEntity.shopNo.eq(shopNo))
//                .fetch();

        List<ShopEntity> shopEntities = queryFactory.select(shopEntity)
                .from(shopEntity)
                .leftJoin(shopEntity.shopTemplateList, shopTemplateEntity)
                .leftJoin(shopTemplateEntity.template, templateEntity)
                .leftJoin(templateEntity.cornerList, templateCornerEntity).fetchJoin()
                .leftJoin(templateCornerEntity.corner, cornerEntity)
                .where(shopEntity.shopNo.eq(shopNo))
                .fetch();

        for (ShopEntity shop : shopEntities) {
            List<ShopTemplateEntity> shopTemplateList = shop.getShopTemplateList();
            for (ShopTemplateEntity shopTemplate : shopTemplateList) {
                TemplateEntity template = shopTemplate.getTemplate();
                List<TemplateCornerEntity> cornerList = template.getCornerList();
                for (TemplateCornerEntity templateCorner : cornerList) {
                    CornerEntity corner = templateCorner.getCorner();
                    String cornerName = corner.getCornerName();
                    log.info("cornerName: {}", cornerName);
                }
            }
        }

        log.info("FINISH");
    }
}

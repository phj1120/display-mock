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

import java.util.Comparator;
import java.util.List;

import static com.displayapi.displayapi.entity.display.QCornerEntity.cornerEntity;
import static com.displayapi.displayapi.entity.display.QShopTemplateEntity.shopTemplateEntity;
import static com.displayapi.displayapi.entity.display.QTemplateCornerEntity.templateCornerEntity;
import static com.displayapi.displayapi.entity.display.QTemplateEntity.templateEntity;
import static com.querydsl.jpa.JPAExpressions.select;

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

    Long shopNo;

    @BeforeEach
    void beforeEach() {
        // 매장 등록
        ShopEntity shopEntity1 = new ShopEntity();
        shopEntity1.setShopName("매장1");
        shopRepository.save(shopEntity1);

        shopNo = shopEntity1.getShopNo();

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
//        List<ShopEntity> shopEntities = queryFactory
//                .select(shopEntity)
//                .from(shopEntity)
//                .leftJoin(shopEntity.shopTemplateList, shopTemplateEntity)
//                .leftJoin(shopTemplateEntity.template, templateEntity)
//                .leftJoin(templateEntity.cornerList, templateCornerEntity)
//                .leftJoin(templateCornerEntity.corner, cornerEntity)
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

//        for (ShopEntity shop : shopEntities) {
//            List<ShopTemplateEntity> shopTemplateList = shop.getShopTemplateList();
//            for (ShopTemplateEntity shopTemplate : shopTemplateList) {
//                TemplateEntity template = shopTemplate.getTemplate();
//                List<TemplateCornerEntity> cornerList = template.getCornerList();
//                for (TemplateCornerEntity templateCorner : cornerList) {
//                    CornerEntity corner = templateCorner.getCorner();
//                    String cornerName = corner.getCornerName();
//                    log.info("cornerName: {}", cornerName);
//                }
//            }
//        }

        log.info("FINISH");
    }

    /**
     * 한방 쿼리로 정보 다 가져오려 했는데, 생각처럼 안 됨.
     * 단방향 매핑으로 바꾸고, 필요시 양방향 매핑으로 변경 하는거로 엔티티 수정 함
     */
    @Commit
    @Test
    void test2() {
        // 매장 정보
        //      템플릿 정보
        //          코너 정보

        // 매장 템플릿 에서 매장에 해당하는 템플릿 조회
        List<ShopTemplateEntity> templates = queryFactory
                .select(shopTemplateEntity)
                .from(shopTemplateEntity)
                .leftJoin(shopTemplateEntity.template, templateEntity).fetchJoin()
                .where(shopTemplateEntity.shop.shopNo.eq(shopNo))
                .fetch();
        templates.sort(Comparator.comparing(ShopTemplateEntity::getShopTemplateNo).reversed());
        TemplateEntity template = templates.get(0).getTemplate();

        // 템플릿에 매핑된 코너 조회.
        Long templateNo = template.getTemplateNo();
        List<TemplateCornerEntity> templateCorners = queryFactory
                .select(templateCornerEntity)
                .from(templateCornerEntity)
                .leftJoin(templateCornerEntity.corner, cornerEntity).fetchJoin()
                .where(templateCornerEntity.template.templateNo.eq(templateNo))
                .fetch();

        for (TemplateCornerEntity templateCorner : templateCorners) {
            log.info("templateCornerName: {}", templateCorner.getTemplateCornerName());
            CornerEntity corner = templateCorner.getCorner();
            log.info("cornerName: {}", corner.getCornerName());
        }
        log.info("FINISH");
    }

    /**
     * join 시점에 sub query 로 가장 최근의 shopTemplate 만 조회하는 로직 추가 하고 싶은데 안되서,
     * where 절에 추가.
     */
    @Test
    void test3() {
        QShopTemplateEntity shopTemplateSub = new QShopTemplateEntity("shopTemplateSub");
        List<TemplateCornerEntity> templateCorners = queryFactory
                .select(templateCornerEntity)
                .from(templateCornerEntity)
                .leftJoin(templateCornerEntity.template, templateEntity)
                .leftJoin(shopTemplateEntity).on(
                        shopTemplateEntity.template.templateNo.eq(templateEntity.templateNo)
                )
                .leftJoin(templateCornerEntity.corner, cornerEntity).fetchJoin()
                .where(
                        shopTemplateEntity.shopTemplateNo.eq(
                                select(shopTemplateSub.shopTemplateNo.max())
                                        .from(shopTemplateSub)
                                        .where(shopTemplateSub.shop.shopNo.eq(shopNo)
                                )
                        )
                )
                .fetch();

        for (TemplateCornerEntity templateCorner : templateCorners) {
            log.info("templateCornerName: {}", templateCorner.getTemplateCornerName());
            CornerEntity corner = templateCorner.getCorner();
            log.info("cornerName: {}", corner.getCornerName());
        }
        log.info("FINISH");
    }
}

-- 템플릿기본
SELECT *
FROM TEMPLATE_ENTITY;

    -- 템플릿매핑정보
    SELECT *
    FROM SHOP_TEMPLATE_ENTITY;

-- 전시매장기본
SELECT *
FROM SHOP_ENTITY;

---------------------------------------------------------------------

-- 템플릿 기본
SELECT *
FROM TEMPLATE_ENTITY;

    -- 템플릿코너매핑정보
    SELECT *
    FROM TEMPLATE_CORNER_ENTITY;

-- 코너기본
SELECT *
FROM CORNER_ENTITY;

---------------------------------------------------------------------

-- -- 템플릿매핑정보
-- SELECT SHOP_TMPL_NO, TMPL_NO, SHOP_NO
-- FROM PR_TMPL_MAPP_INFO;
--
--     -- 전시세트정보
--     SELECT DISP_SET_SEQ, TMPL_CONR_NO -- , SHOP_TMPL_NO
--     FROM PR_CONR_SET_INFO;
--
--         -- 코너컨턴츠정보
--         SELECT DISP_SET_SEQ, CONR_TGT_CD, CONR_CONT_NO
--         FROM PR_CONR_CONT_INFO;
--
-- -- 템플릿코너매핑정보
-- SELECT TMPL_CONR_NO, CONR_NO -- , TMPL_NO
-- FROM PR_TMPL_CONR_MAPP_INFO;


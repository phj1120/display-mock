// 매장 번호로 템플릿 정보와 해당 템플릿에 꽂힌 코너 정보 조회.
select *
from SHOP_ENTITY se
    left join SHOP_TEMPLATE_ENTITY ste on se.SHOP_NO = ste.SHOP_SHOP_NO -- 가장 최근거 하나만 가져와야 되는데..
    left join TEMPLATE_ENTITY te on te.TEMPLATE_NO = ste.TEMPLATE_TEMPLATE_NO
    left join TEMPLATE_CORNER_ENTITY tce on tce.TEMPLATE_TEMPLATE_NO = te.TEMPLATE_NO
    left join CORNER_ENTITY ce on ce.CORNER_NO = tce.CORNER_CORNER_NO
where SHOP_NO = '1'
;

select
    se1_0.shop_no,
    se1_0.shop_name
from
    shop_entity se1_0
        left join shop_template_entity stl1_0 on se1_0.shop_no=stl1_0.shop_shop_no
        left join template_entity t1_0 on t1_0.template_no=stl1_0.template_template_no
        left join template_corner_entity cl1_0 on t1_0.template_no=cl1_0.template_template_no
where se1_0.shop_no=1


// 세트 추가 후. 매장 번호로 코너에 꽂힌 정보 조회


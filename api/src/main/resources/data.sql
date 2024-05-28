insert into shop_entity (shop_name, shop_no)
values ('매장1', 1)
;

insert into corner_entity (corner_name, corner_no)
values ('코너1', 1)
     , ('코너2', 2)
;

insert into template_entity (template_name, template_no)
values ('템플릿1', 1)
;
insert into shop_template_entity (shop_shop_no, shop_template_name, template_template_no, shop_template_no)
values (1, '매장1 - 템플릿1', 1, 1)
;
insert into template_corner_entity (corner_corner_no, template_template_no, template_corner_name, template_corner_no)
values (1, 1, '템플릿1 - 코너1', 1)
;

insert into template_entity (template_name, template_no)
values ('템플릿2', 2)
;
insert into shop_template_entity (shop_shop_no, shop_template_name, template_template_no, shop_template_no)
values (1, '매장1 - 템플릿2', 2, 2)
;
insert into template_corner_entity (corner_corner_no, template_template_no, template_corner_name, template_corner_no)
values (1, 2, '템플릿2 - 코너1', 2)
     , (2, 2, '템플릿2 - 코너2', 3)
;



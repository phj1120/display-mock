DROP SEQUENCE shop_seq1;
DROP SEQUENCE corner_seq1;
DROP SEQUENCE template_seq1;
DROP SEQUENCE shop_template_seq1;
DROP SEQUENCE template_corner_seq1;

CREATE SEQUENCE shop_seq1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE corner_seq1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE template_seq1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE shop_template_seq1 START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE template_corner_seq1 START WITH 1 INCREMENT BY 1;

insert into shop (shop_name, shop_no)
values ('매장1', 1)
;

insert into corner (corner_name, corner_no, corner_id)
values ('코너1', 1, 'CORNER_1')
     , ('코너2', 2, 'CORNER_2')
;

insert into template (template_name, template_no, TEMPLATE_ID)
values ('템플릿1', 1, 'TEMPLATE_1')
;
insert into shop_template (shop_shop_no, shop_template_name, template_template_no, shop_template_no)
values (1, '매장1 - 템플릿1', 1, 1)
;
insert into template_corner (corner_corner_no, template_template_no, template_corner_name, template_corner_no)
values (1, 1, '템플릿1 - 코너1', 1)
;

insert into template (template_name, template_no, TEMPLATE_ID)
values ('템플릿2', 2, 'TEMPLATE_2')
;
insert into shop_template (shop_shop_no, shop_template_name, template_template_no, shop_template_no)
values (1, '매장1 - 템플릿2', 2, 2)
;
insert into template_corner (corner_corner_no, template_template_no, template_corner_name, template_corner_no)
values (1, 2, '템플릿2 - 코너1', 2)
     , (2, 2, '템플릿2 - 코너2', 3)
;



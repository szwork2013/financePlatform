
--1.根据code表中的所有基金代码构建p_product_manage表的inset语句
SELECT
    'INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES ('
    || nextval('SRC') ||', ''' || c.code || ''', '''|| c.value ||
    ''', ''FP.PRODUCT.TYPE.1'', ''FP.PRODUCT.MANAGE.STATUS.2'', null, null, null, null, ''FP.RECOMMEND.TYPE.2'', ''FP.RECOMMEND.FLAG.2'', null, ''GYS'', null, ''Y'', ''2014-12-01 00:00:00.0'', ''2016-01-01 00:00:00.0'', null, 231, 112, ''2014-12-06 11:10:54.439'', ''2014-12-06 11:10:54.439'', null, null);'
FROM
    code c
WHERE
    c.code IN (
    (   --货币基金
        SELECT
            f.fundcode
        FROM
            fundnav f
        WHERE
            1=1
        AND f.on_sale = 1
        AND f.purchase_state = 1
        --所有A型基金和没有英文的基金
        AND f.fundname !~ '.*[B-Z]+.*'
        AND f.is_monetary = 1
        AND f.is_stf = 0
        ORDER BY
            f.percent_seven_days DESC,
            f.fundcode limit 30 offset 0)
UNION
    (   --定期理财
        SELECT
            f.fundcode
        FROM
            fundnav f
        WHERE
            1=1
        AND f.on_sale = 1
        AND f.purchase_state = 1
        --所有A型基金和没有英文的基金
        AND f.fundname !~ '.*[B-Z]+.*'
        AND f.is_monetary = 0
        AND f.is_stf = 1
        ORDER BY
            f.percent_seven_days DESC,
            f.fundcode limit 30 offset 0) );
--2.执行构建好的inset语句

--3.初始化购买人数
update public.p_product_manage set init_buyed_count = 200 + cast( floor(random()*150) as int),one_month_buyed_count = 200 + cast( floor(random()*150) as int);
--4.更新所有产品的状态为上架
update public.p_product_manage set product_status = 'FP.PRODUCT.MANAGE.STATUS.1';





INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (155324, '000204', '国富货币B', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, null, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.2', null, 'GYS', null, 'Y', '2014-12-01 00:00:00.0', '2015-01-01 00:00:00.0', null, 432, 324, '2014-12-06 11:13:29.371', '2014-12-06 11:13:29.371', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (48401, '482002', '工银货币', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, null, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.3', null, 'GYS', null, 'Y', '2014-11-01 08:00:00.0', '2015-01-01 08:00:00.0', null, 36557, 5699, '2014-11-27 13:14:56.943', '2014-12-06 10:54:25.327', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (46142, '202301', '南方增利A', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, null, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.3', null, 'GYS', null, 'Y', '2014-01-31 08:00:00.0', '2015-01-01 08:00:00.0', null, 69844, 598, '2014-11-26 22:48:51.93', '2014-12-06 10:54:11.29', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (46143, '161608', '融通货币A', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, null, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.2', null, 'GYS', null, 'Y', '2014-01-31 08:00:00.0', '2015-01-01 08:00:00.0', null, 6987, 598, '2014-11-26 22:49:31.607', '2014-12-06 10:54:19.1', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (140400, '090023', '大成21债A', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, 3, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.2', null, 'GYS', null, 'Y', '2014-12-01 08:00:00.0', '2015-01-01 08:00:00.0', null, 111, 111, '2014-12-04 09:30:23.129', '2014-12-06 10:54:33.434', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (141900, '000855', '上投天添盈A', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, 11, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.3', null, 'GYS', '', 'Y', '2014-12-01 08:00:00.0', '2015-01-01 08:00:00.0', null, 111, 111, '2014-12-04 11:33:27.135', '2014-12-06 10:54:40.78', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (155321, '000013', '易方达天天R', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, null, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.3', null, 'GYS', null, 'Y', '2014-12-01 00:00:00.0', '2015-01-01 00:00:00.0', null, 2345, 323, '2014-12-06 11:05:27.883', '2014-12-06 11:05:27.883', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (151982, '240006', '华宝现A', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, 1, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.2', null, 'GYS', null, 'Y', '2014-12-01 08:00:00.0', '2014-12-31 08:00:00.0', null, 111, 11, '2014-12-05 20:24:24.29', '2014-12-06 10:54:44.669', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (155320, '000010', '易方达天天B', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, null, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.2', null, 'GYS', null, 'Y', '2014-12-01 00:00:00.0', '2015-01-01 00:00:00.0', null, 1111, 222, '2014-12-06 11:04:32.966', '2014-12-06 11:04:32.966', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (46141, '519505', '海富货A', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, 1, 'FP.RECOMMEND.TYPE.1', 'FP.RECOMMEND.FLAG.1', null, 'GYS', null, 'Y', '2014-01-31 08:00:00.0', '2016-03-01 00:00:00.0', null, 36894, 6984, '2014-11-26 22:48:16.365', '2015-01-04 10:52:17.236', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (155322, '000198', '天弘增利宝', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, null, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.1', null, 'GYS', null, 'Y', '2014-12-01 00:00:00.0', '2015-01-01 00:00:00.0', null, 231, 112, '2014-12-06 11:10:54.439', '2014-12-06 11:10:54.439', null, null);
INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES (155323, '000203', '国富货币A', 'FP.PRODUCT.TYPE.1', 'FP.PRODUCT.MANAGE.STATUS.1', null, null, null, null, 'FP.RECOMMEND.TYPE.2', 'FP.RECOMMEND.FLAG.3', null, 'GYS', null, 'Y', '2014-12-01 00:00:00.0', '2015-01-01 00:00:00.0', null, 345, 12, '2014-12-06 11:12:09.803', '2014-12-06 11:12:09.803', null, null);


select 'INSERT INTO public.p_product_manage (id, product_code, product_name, product_type, product_status, begin_date, end_date, temp_stop_date, priority_level, recommend_type, recommend_flag, recommend_desc, supplier_code, url, is_grab, up_begin_time, down_end_time, product_desc, init_buyed_count, one_month_buyed_count, create_time, update_time, create_by, update_by) VALUES ('|| nextval('SRC') ||', '''  || c.code || ''', '''|| c.value ||''', ''FP.PRODUCT.TYPE.1'', ''FP.PRODUCT.MANAGE.STATUS.1'', null, null, null, null, ''FP.RECOMMEND.TYPE.2'', ''FP.RECOMMEND.FLAG.1'', null, ''GYS'', null, ''Y'', ''2014-12-01 00:00:00.0'', ''2016-01-01 00:00:00.0'', null, 231, 112, ''2014-12-06 11:10:54.439'', ''2014-12-06 11:10:54.439'', null, null);' from code c;
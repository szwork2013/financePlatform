
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

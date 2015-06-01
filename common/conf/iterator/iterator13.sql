DROP TABLE IF EXISTS c_financial_planner;
CREATE TABLE
    c_financial_planner
    (
        id INTEGER NOT NULL,
        mobile_phone CHARACTER VARYING(15) NOT NULL,
        manager_id INTEGER,
        name CHARACTER VARYING(60) NOT NULL,
        create_time TIMESTAMP,
        update_time TIMESTAMP,
        DELETED              boolean                        null
    );

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (44, '理财师信息', 'customer:financialPlanner', '2', 42, 'menu', '#/dashboard/financialPlanner', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476',FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (44, 1, 44, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (45, '理财师客户信息', 'customer:plannerCustomer', '3', 42, 'menu', '#/dashboard/plannerCustomer', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476',FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (45, 1, 45, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);


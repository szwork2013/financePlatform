alter table parameter alter column value type character varying(1000);

insert into parameter ( id, description, name, value, status)
values ((SELECT nextval('SRC')),
'2015年国定假日安排，每天之间用；隔开，格式yyyy-MM-dd',
'HOLIDAY_DATE',
'2015-01-01;2015-01-02;2015-01-03;2015-02-18;2015-02-19;2015-02-20;2015-02-21;2015-02-22;2015-02-23;2015-02-24;2015-04-04;2015-04-05;2015-04-06;2015-05-01;2015-05-02;2015-05-03;2015-06-20;2015-06-21;2015-06-22;2015-09-26;2015-09-27;2015-10-01;2015-10-02;2015-10-03;2015-10-04;2015-10-05;2015-10-06;2015-10-07',
'Y');



DROP VIEW view_message_list;

CREATE OR REPLACE VIEW view_message_list AS
 SELECT t.id,
    t.message_rule_id,
    t.title,
    t.summary,
    t.create_time,
    t.send_type,
    t.customer_id,
    t.stay_day_ind
   FROM ( SELECT mst.id,
                 mst.message_rule_id,
                 mst.title,
                 "substring"(mst.content::text, 1, 50) AS summary,
                 mst.create_time,
                'FP.SEND.TYPE.1'::text AS send_type,
                c.customer_id,
                mr.stay_day_ind
           FROM c_message_rule mr, c_message_sms_txn mst, c_customer c
          WHERE mr.id = mst.message_rule_id AND c.mobile::text = mst.mobile::text AND mr.msg_center_ind::text = 'Y'::text AND mr.sms_ind::text = 'Y'::text and mr.status = 'Y'::text
        UNION
         SELECT pt.id,
                pt.message_rule_id,
                pt.title,
                pt.summary,
                pt.create_time,
                pt.send_type,
                pt.customer_id,
                mr.stay_day_ind
           FROM c_message_rule mr,
            ( SELECT cpt.id,
                     cpt.message_rule_id,
                     cpt.title,
                     "substring"(cpt.content::text, 1, 50) AS summary,
                     cpt.create_time,
                     'FP.SEND.TYPE.2'::text AS send_type,
                     cg.customer_id
               FROM  c_message_push_txn cpt LEFT JOIN c_customer_group cg ON cpt.group_id = cg.group_id
              UNION
             SELECT  cmpt.id,
                     cmpt.message_rule_id,
                     cmpt.title,
                     "substring"(cmpt.content::text, 1, 50) AS summary,
                     cmpt.create_time,
                     'FP.SEND.TYPE.3'::text AS send_type,
                     cmpt.customer_id
               FROM  c_customer_msg_push_txn cmpt) pt
          WHERE mr.id = pt.message_rule_id AND mr.msg_center_ind::text = 'Y'::text AND mr.sms_ind::text = 'N'::text and mr.status = 'Y'::text) t
  ORDER BY t.create_time DESC;




insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE_PURCHASE_SHOWINCOME','显示收益','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE_PURCHASE_CONFIRMINCOME','确认份额并开始计算收益','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE_PURCHASE_APPLY','购买申请提交','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE_REDEEM_CONFIRMTIME','1~3个交易日后','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE_REDEEM_CONFIRM','预计取现到银行卡','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE_REDEEM_APPLY','取现申请提交','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'调用p2p接口开关，N不调用，否则调用','P2P_INTERFACE_SWITCH','Y','Y');


update c_message_rule set content='尊敬的用户，恭喜您完成了首次购买，【金豆荚】赠送您{0}，请在“财富”中的“我的金豆荚”中查看现金红包。现在分享活动给朋友还能抽奖APPLE WATCH！【金豆荚】祝您理财愉快！',
content_sms='尊敬的用户，恭喜您完成了首次购买，赠送您{0}元红包，请在“财富”中的“我的金豆荚”中查看现金红包。现在分享活动给朋友还能抽奖APPLE WATCH！详情点击{1}。祝您理财愉快！【金豆荚】',
content_push='恭喜您完成了首次购买，【金豆荚】赠送您{0}，现在分享红包活动即有机会抽奖APPLE WATCH！' where code='FIRST_PURCHASE' and status='Y';


update c_message_rule set content_sms='尊敬的用户，恭喜您在金豆荚注册成功，获得{0}；此金豆需要您在金豆荚首次交易后才可使用。我们为您准备了丰富多样的理财产品，请在“理财”中购买。详情点击{1}。【金豆荚】祝您理财愉快！'
where code='REGISTER_BEAN' and status='Y';



create table  f_show_statistics
(
       Id                INTEGER not null,
       stat_count        INTEGER,
       product_Code      VARCHAR(20),
       stat_type         VARCHAR(10),
       create_time       TIMESTAMP,
       update_time       TIMESTAMP
);
alter  table f_show_statistics add constraint PK_f_show_statistics_Id primary key (Id);
comment on table f_show_statistics is '产品显示信息统计';
comment on column f_show_statistics.Id is '编号';
comment on column f_show_statistics.stat_count is '统计数量';
comment on column f_show_statistics.product_Code is '产品Code';
comment on column f_show_statistics.stat_type is '统计类型 0-注册数量统计，1-产品购买数量统计';
comment on column f_show_statistics.create_time is '创建时间';
comment on column f_show_statistics.update_time is '修改时间';


create table  t_trade_status_change_info
(
       Id                INTEGER not null,
       trade_no          VARCHAR(20),
       trade_time        TIMESTAMP,
       status_change_time VARCHAR(200),
       trade_type        VARCHAR(20),
       status_desc       VARCHAR(50),
       create_time       TIMESTAMP,
       update_time       TIMESTAMP
);
alter  table t_trade_status_change_info add constraint PK_t_trade_snfo_Id primary key (Id);
comment on table t_trade_status_change_info is '交易状态变更记录表';
comment on column t_trade_status_change_info.Id is '编号';
comment on column t_trade_status_change_info.trade_no is '交易流水号';
comment on column t_trade_status_change_info.trade_time is '交易时间';
comment on column t_trade_status_change_info.status_change_time is '状态变更时间';
comment on column t_trade_status_change_info.trade_type is '交易类型 FP.TRADE.TYPE.1-申购，FP.TRADE.TYPE.2-赎回，FP.TRADE.TYPE.3-分红，';
comment on column t_trade_status_change_info.status_desc is '状态描述';
comment on column t_trade_status_change_info.create_time is '创建时间';
comment on column t_trade_status_change_info.update_time is '修改时间';


/* done at 2015-02-31*/
create table  F_activity_report
(
       Id                INTEGER not null,
       active_report_desc VARCHAR(100),
       active_report_type VARCHAR(300),
       channel           VARCHAR(10),
       create_time       TIMESTAMP
);
alter  table F_activity_report add constraint PK_F_activity_report_Id primary key (Id);
comment on table F_activity_report is '活动播报';
comment on column F_activity_report.Id is '编号';
comment on column F_activity_report.active_report_desc is '活动播报描述';
comment on column F_activity_report.active_report_type is '奖励活动播报 0-注册活动，1-奖励活动';
comment on column F_activity_report.channel is '渠道 0-web端，1-移动端';
comment on column F_activity_report.create_time is '创建时间';

--GRANT ALL ON TABLE view_message_list TO uat_user;
--GRANT ALL ON TABLE f_show_statistics TO uat_user;
--GRANT ALL ON TABLE F_activity_report TO uat_user;
--GRANT ALL ON TABLE t_trade_status_change_info TO uat_user;

DELETE FROM public.O_USER_ROLE;
DELETE FROM public.O_ROLE_RESOURCE;
DELETE FROM public.O_P;
DELETE FROM public.O_USER;
DELETE FROM public.O_ROLE;
DELETE FROM public.O_RESOURCE;

INSERT INTO public.o_user (id, username, password, status, deleted, create_time, update_time)
VALUES (1, 'admin', 'admin', 'Y', FALSE, '2015-01-28 13:15:37.656', '2015-01-28 13:15:41.417');

INSERT INTO public.o_role (id, code, name, "DESC", create_time, update_time, deleted)
VALUES (1, 'system', '管理员角色', '管理员角色', '2015-01-28 13:20:27.567', '2015-01-28 13:20:28.573', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (1, '首页', 'home', '1', 0, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (2, '系统管理', 'system', '1', 1, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (3, '消息管理', 'message', '1', 1, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (4, '产品管理', 'product', '1', 1, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (5, '活动管理', 'activity', '1', 1, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (6, '权限管理', 'authority', '1', 1, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (7, '问题反馈', 'feedback', '1', 1, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (8, '银行管理', 'bank', '1', 1, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);

--system
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (9, '定时任务', 'system:job', '1', 2, 'menu', 'dashboard.tasks', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (10, '基金公司', 'system:fund:company', '3', 2, 'menu', 'dashboard.fundcompanies', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (11, '供应商', 'system:supplier', '4', 2, 'menu', 'dashboard.supplier', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (12, '验证码', 'system:verifyCode', '6', 2, 'menu', 'dashboard.verifyCode', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (13, '字典表', 'system:dict', '7', 2, 'menu', 'dashboard.dict', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (14, '参数管理', 'system:dict', '7', 2, 'menu', 'dashboard.parameter', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);

--message
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (15, '短信统计', 'message:sms', '1', 3, 'menu', 'dashboard.sms', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (16, '群组管理', 'message:group', '2', 3, 'menu', 'dashboard.group', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (17, '消息规则', 'message:role', '3', 3, 'menu', 'dashboard.messageRole', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (18, '消息推送计划配置', 'message:config', '4', 3, 'menu', 'dashboard.messageConfig', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (19, '规则与活动映射管理', 'message:mapping', '5', 3, 'menu', 'dashboard.messageMapping', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);

--product
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (20, '产品信息', 'product:management', '1', 4, 'menu', 'dashboard.product', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (21, '基金信息', 'product:fund', '2', 4, 'menu', 'dashboard.fund', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);


--feedback
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (22, '反馈', 'feedback:index', '1', 7, 'menu', 'dashboard.feedback', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (23, '问题', 'feedback:question', '2', 7, 'menu', 'dashboard.question', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);

--bank
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (24, '银行管理', 'bank:manage', '1', 8, 'menu', 'dashboard.bank', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (25, '银行活期', 'bank:deposit', '2', 8, 'menu', 'dashboard.deposit', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);

--authority
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (26, '用户管理', 'authority:user', '1', 6, 'menu', 'dashboard.user', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (27, '角色管理', 'authority:role', '2', 6, 'menu', 'dashboard.role', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (28, '资源管理', 'authority:resource', '2', 6, 'menu', 'dashboard.resource', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);


INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (1, 1, 1, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (2, 1, 2, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (3, 1, 3, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (4, 1, 4, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (5, 1, 5, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (6, 1, 6, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (7, 1, 7, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (8, 1, 8, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (9, 1, 9, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (10, 1, 10, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (11, 1, 11, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (12, 1, 12, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (13, 1, 13, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (14, 1, 14, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (15, 1, 15, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (16, 1, 16, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (17, 1, 17, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (18, 1, 18, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (19, 1, 19, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (20, 1, 20, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (21, 1, 21, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (22, 1, 22, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (23, 1, 23, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (24, 1, 24, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (25, 1, 25, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (26, 1, 26, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (27, 1, 27, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (28, 1, 28, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);


INSERT INTO public.o_user_role (id, user_id, role_id, create_time, update_time, deleted)
VALUES (1, 1, 1, '2015-01-28 15:05:48.918', '2015-01-28 15:05:50.854', FALSE);


--activity
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (29, '红包取现兑换结果', 'activity:red', '7', 5, 'menu', 'dashboard.red', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (29, 1, 29, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (35, '活动场景', 'activity:scene', '1', 5, 'menu', 'dashboard.scene', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (35, 1, 35, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (36, '奖励类型', 'activity:rewardType', '2', 5, 'menu', 'dashboard.rewardType', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (36, 1, 36, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (37, '分享信息', 'activity:share', '3', 5, 'menu', 'dashboard.share', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (37, 1, 37, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (38, '活动列表', 'activity:list', '4', 5, 'menu', 'dashboard.list', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (38, 1, 38, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (39, '兑换场景', 'activity:exchangescene', '5', 5, 'menu', 'dashboard.exchangescene', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (39, 1, 39, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (40, '金豆取现管理', 'activity:exchangebean', '6', 5, 'menu', 'dashboard.exchangebean', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (40, 1, 40, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted)
VALUES (41, '活动错误信息', 'activity:errorMsg', '8', 5, 'menu', 'dashboard.errorMsg', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted)
VALUES (41, 1, 41, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);


--statistics
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (34, '汇总统计', 'statistics:tradeSummary', '1', 30, 'menu', 'dashboard.statisticsUnPurchase', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476',FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (34, 1, 34, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (30, '统计管理', 'statistics', '1', 1, 'menu', NULL, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (31, '推荐人', 'statistics:referrer', '2', 30, 'menu', 'dashboard.referrer', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);

INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (30, 1, 30, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (31, 1, 31, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (32, '首次购买明细', 'statistics:purchase', '3', 30, 'menu', 'dashboard.statisticsPurchase', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (32, 1, 32, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (33, '注册未购买', 'statistics:unpurchase', '4', 30, 'menu', 'dashboard.statisticsUnPurchase', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476',FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (33, 1, 33, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);

--customer
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (42, '客户管理', 'customer', '1', 1, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476',FALSE);
INSERT INTO public.o_resource (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (43, '客户基本信息', 'customer:index', '1', 42, 'menu', 'dashboard.customer', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476',FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (42, 1, 42, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);
INSERT INTO public.o_role_resource (id, role_id, resource_id, create_time, update_time, deleted) VALUES (43, 1, 43, '2015-01-28 14:54:40.570', '2015-01-28 14:54:41.312', FALSE);


----------------------------------------------------------------------------------------------------
--交易金额汇总
CREATE OR REPLACE VIEW total_trade_amount(trade_date, day_in_amount, in_amount_total, day_out_amount, out_amount_total) AS
  WITH day_trade_amount AS (
      SELECT
        DATE(t.trade_time) AS trade_date,
        SUM(CASE WHEN t.type = 'FP.TRADE.TYPE.1' THEN t.trade_amount ELSE 0 END)    AS day_in_amount,
        SUM(CASE WHEN t.type = 'FP.TRADE.TYPE.2' THEN t.trade_amount ELSE 0 END)    AS day_out_amount
      FROM t_trade t JOIN c_customer c ON t.cust_id = c.customer_id
      WHERE t.trade_amount > 0
      GROUP BY DATE(t.trade_time)
      ORDER BY DATE(t.trade_time)
  )
  SELECT
    d1.trade_date, d1.day_in_amount,
    SUM(d1.day_in_amount) OVER (ORDER BY d1.trade_date) AS in_amount_total,
    d1.day_out_amount,
    SUM(d1.day_out_amount)OVER (ORDER BY d1.trade_date) AS out_amount_total
  FROM day_trade_amount d1;

--用户注册汇总
CREATE OR REPLACE VIEW total_registration_info(registration_date, registration_count, registration_total) AS
  WITH registration_info AS (
      SELECT
        DATE(c.create_time) AS registration_date,
        COUNT(c.id)         AS registration_count
      FROM c_customer c
      GROUP BY DATE(c.create_time)
  )
  SELECT
    r.registration_date,
    r.registration_count,
    SUM(r.registration_count) OVER (ORDER BY r.registration_date) AS registration_total
  FROM registration_info r;


--新增成功购买用户汇总
CREATE OR REPLACE VIEW total_purchase_customer(purchase_date, in_customer_count, total_in_customer_count, out_customer_count, total_out_customer_count ) AS
  WITH day_purchase_customer AS (
      SELECT
        DATE(p.trade_time) AS purchase_date,
        SUM(CASE WHEN (p.type = 'FP.TRADE.TYPE.1') THEN 1 ELSE 0 END)    AS in_customer_count,
        SUM(CASE WHEN (p.type = 'FP.TRADE.TYPE.2') THEN 1 ELSE 0 END)    AS out_customer_count
      FROM (SELECT DISTINCT ON (t.cust_id) t.cust_id, t.trade_time, t.type FROM t_trade t JOIN c_customer c ON t.cust_id = c.customer_id ORDER BY t.cust_id, t.trade_time) p
      GROUP BY DATE(p.trade_time)
      ORDER BY DATE(p.trade_time)
  )
  SELECT
    dpc.purchase_date,
    dpc.in_customer_count,
    SUM(dpc.in_customer_count)
    OVER (ORDER BY dpc.purchase_date) AS total_in_customer_count,
    dpc.out_customer_count,
    SUM(dpc.out_customer_count)
    OVER (ORDER BY dpc.purchase_date) AS total_out_customer_count
  FROM
    day_purchase_customer dpc;

-- 交易金额汇总+last_date
CREATE OR REPLACE VIEW total_trade_summary(
    summary_date, last_date, trade_date, day_in_amount, in_amount_total, day_out_amount, out_amount_total,
    registration_date, registration_count, registration_total,
    purchase_date, in_customer_count, total_in_customer_count, out_customer_count, total_out_customer_count
) AS
  WITH trade_summary AS (
      SELECT t_1.trade_date,
        t_1.day_in_amount,
        t_1.in_amount_total,
        t_1.day_out_amount,
        t_1.out_amount_total,
        r.registration_date,
        r.registration_count,
        r.registration_total,
        p.purchase_date,
        p.in_customer_count,
        p.total_in_customer_count,
        p.out_customer_count,
        p.total_out_customer_count
      FROM
        total_trade_amount t_1
        LEFT JOIN total_registration_info r ON t_1.trade_date = r.registration_date
        LEFT JOIN total_purchase_customer p ON t_1.trade_date = p.purchase_date
      ORDER BY
        t_1.trade_date
  )
  SELECT DISTINCT
  ON
    (
    t.trade_date)
    t.trade_date AS summary_date,
    s.trade_date AS last_date,
    t.trade_date,
    t.day_in_amount,
    t.in_amount_total,
    t.day_out_amount,
    t.out_amount_total,
    t.registration_date,
    t.registration_count,
    t.registration_total,
    t.purchase_date,
    t.in_customer_count,
    t.total_in_customer_count,
    t.out_customer_count,
    t.total_out_customer_count
  FROM trade_summary t, trade_summary s
  WHERE t.trade_date > s.trade_date
  ORDER BY t.trade_date, s.trade_date DESC;

--递归-用户注册汇总
CREATE OR REPLACE VIEW view_total_registration_info(vw_registration_date, vw_registration_total) AS
  WITH RECURSIVE trs AS (
    SELECT tts.summary_date AS real_date, tts.summary_date, tts.last_date, tts.registration_date, tts.registration_total
    FROM total_trade_summary tts UNION ALL
    SELECT trs.real_date, s.summary_date, s.last_date, s.registration_date, s.registration_total
    FROM total_trade_summary s, trs
    WHERE s.summary_date = trs.last_date
  )
  SELECT
    DISTINCT ON (ts.summary_date) ts.summary_date AS vw_registration_date, t.registration_total AS vw_registration_total
  FROM trs t JOIN total_trade_summary ts ON t.real_date = ts.summary_date
  WHERE t.registration_date IS NOT NULL
  ORDER BY ts.summary_date, t.registration_date DESC;

--递归-新增成功购买用户汇总
CREATE OR REPLACE VIEW view_total_purchase_customer (vw_purchase_date, vw_total_in_customer_count, vw_total_out_customer_count) AS
  WITH RECURSIVE trs AS (
    SELECT tts.summary_date AS real_date, tts.summary_date, tts.last_date, tts.purchase_date, tts.total_in_customer_count, tts.total_out_customer_count
    FROM total_trade_summary tts
    UNION ALL
    SELECT trs.real_date, s.summary_date, s.last_date, s.purchase_date, s.total_in_customer_count, s.total_out_customer_count
    FROM total_trade_summary s, trs
    WHERE s.summary_date = trs.last_date
  )
  SELECT DISTINCT ON (ts.summary_date) ts.summary_date AS vw_purchase_date, t.total_in_customer_count  AS vw_total_in_customer_count, t.total_out_customer_count AS vw_total_out_customer_count
  FROM trs t JOIN total_trade_summary ts  ON t.real_date = ts.summary_date
  WHERE t.purchase_date IS NOT NULL
  ORDER BY ts.summary_date, t.purchase_date DESC;










/*权限脚本*/
/*
GRANT ALL ON TABLE view_message_list TO uat_user;
GRANT ALL ON TABLE f_show_statistics TO uat_user;
GRANT ALL ON TABLE F_activity_report TO uat_user;
GRANT ALL ON TABLE t_trade_status_change_info TO uat_user;
GRANT ALL ON TABLE F_activity_report TO uat_user;

GRANT ALL ON TABLE total_trade_amount TO uat_user;
GRANT ALL ON TABLE total_registration_info TO uat_user;
GRANT ALL ON TABLE total_purchase_customer TO uat_user;
GRANT ALL ON TABLE total_trade_summary TO uat_user;
GRANT ALL ON TABLE view_total_registration_info TO uat_user;
GRANT ALL ON TABLE view_total_purchase_customer TO uat_user;

 */--GRANT ALL ON TABLE view_message_list TO uat_user;
--GRANT ALL ON TABLE f_show_statistics TO uat_user;
--GRANT ALL ON TABLE F_activity_report TO uat_user;
--GRANT ALL ON TABLE t_trade_status_change_info TO uat_user;

ALTER TABLE c_share_info ADD COLUMN parent_id INT8;
ALTER TABLE c_share_info ADD COLUMN ref_id VARCHAR (20);
ALTER TABLE c_short_url ADD COLUMN mobile VARCHAR (20);

update qrtz_job_details set job_class_name = 'com.sunlights.op.' || job_class_name;

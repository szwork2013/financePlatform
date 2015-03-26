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
  --GRANT ALL ON TABLE view_message_list TO postgres;




insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.PURCHASE.SHOWINCOME','显示收益','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.PURCHASE.CONFIRMINCOME','确认份额并开始计算收益','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.PURCHASE.APPLY','购买申请提交','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.REDEEM.CONFIRMTIME','1~3个交易日后','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.REDEEM.CONFIRM','预计取现到银行卡','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.REDEEM.APPLY','取现申请提交','Y');







/* uat prd环境已执行

alter table c_message_push_txn alter column content type character varying(1000);
alter table c_message_rule alter column content type character varying(1000);
alter table c_customer_msg_push_txn alter column content type character varying(1000);

  */

insert into dict (id, code_cat, code_key, code_val, seq_no, create_by, create_time, status, sys_ind, magic, remarks)
values ((select nextval('src')), 'FP.PUSH.TYPE', '6', '失败公告', 6, 'system', current_timestamp , 'Y', 'Y', null, null);

insert into dict (id, code_cat, code_key, code_val, seq_no, create_by, create_time, status, sys_ind, magic, remarks)
values ((select nextval('src')), 'FP.PUSH.TYPE', '5', '指定公告', 5, 'system', current_timestamp , 'Y', 'Y', null, '由后台操作人员手动发出的指定公告');


insert into c_message_rule_mapping (id, method_name, rule_code, message_type, scene, activity_id, status)
values ((select nextval('src')), 'genVerificationCode', 'VERIFY_CODE', 'FP.PUSH.TYPE.1', null, null, 'Y');

insert into c_message_rule_mapping (id, method_name, rule_code, message_type, scene, activity_id, status)
values ((select nextval('src')), 'uploadExchangeBean', 'EXCHANGE_BEAN_SUC', 'FP.PUSH.TYPE.2', 'EXC002', null, 'Y');

insert into c_message_rule_mapping (id, method_name, rule_code, message_type, scene, activity_id, status)
values ((select nextval('src')), 'uploadExchangeBean', 'EXCHANGE_BEAN_FAIL', 'FP.PUSH.TYPE.6', 'EXC002', null, 'Y');

insert into c_message_rule_mapping (id, method_name, rule_code, message_type, scene, activity_id, status)
values ((select nextval('src')), 'uploadRedPacket', 'EXCHANGE_RED_PACKET_SUC', 'FP.PUSH.TYPE.2', 'EXC001', null, 'Y');


alter table c_message_sms_txn alter column mobile type character varying(6000);


alter table c_message_rule add constraint uk_code unique(code);

ALTER TABLE c_message_rule ADD COLUMN stay_days bigint;
update c_message_rule set stay_days = 1;


DROP VIEW view_message_list;
CREATE OR REPLACE VIEW view_message_list AS
 SELECT pt.id,
    pt.message_rule_id,
    pt.title,
    pt.summary,
    pt.create_time,
    pt.send_type,
    pt.customer_id,
    date_trunc('day'::text, pt.create_time + (((mr.stay_days - 1) || 'day'::text)::interval)) AS show_time
   FROM c_message_rule mr,
    ( SELECT cpt.id,
            cpt.message_rule_id,
            cpt.title,
            "substring"(cpt.content::text, 1, 50) AS summary,
            cpt.create_time,
            'FP.SEND.TYPE.2'::text AS send_type,
            cg.customer_id
           FROM c_message_push_txn cpt
             LEFT JOIN c_customer_group cg ON cpt.group_id = cg.group_id
        UNION
         SELECT cmpt.id,
            cmpt.message_rule_id,
            cmpt.title,
            "substring"(cmpt.content::text, 1, 50) AS summary,
            cmpt.create_time,
            'FP.SEND.TYPE.3'::text AS send_type,
            cmpt.customer_id
           FROM c_customer_msg_push_txn cmpt) pt
  WHERE mr.id = pt.message_rule_id AND mr.msg_center_ind::text = 'Y'::text AND mr.status::text = 'Y'::text
  ORDER BY pt.create_time DESC;
  /*GRANT SELECT,CREATE,UPDATE,DELETE ON TABLE view_message_list TO uat_user; */



ALTER TABLE c_customer_msg_setting ADD COLUMN platform character varying(50);
COMMENT ON COLUMN c_customer_msg_setting.platform IS 'android , ios';

update c_customer_msg_setting set platform = 'ios';




--认证信息表
create table c_Authentication
(
       Id                INTEGER not null,
       PASSWORD          VARCHAR(50),
       mobile            VARCHAR(20),
       channel           VARCHAR(10),
       create_time       timestamp,
       update_time       timestamp
);
alter  table c_Authentication
       add constraint PK_c_Authentication_Id primary key (Id);
comment on table c_Authentication is '认证信息表';
comment on column c_Authentication.Id is 'ID';
comment on column c_Authentication.mobile is '用户名或手机号 用户名或手机号';
comment on column c_Authentication.channel is '渠道号 channel 0-ios、1-web端 2android';
comment on column c_Authentication.create_time is '创建时间';
comment on column c_Authentication.update_time is '修改时间';

--版本规则配置表
create table  c_version_rule_config
(
       Id                INTEGER not null,
       min_support_Version VARCHAR(10),
       max_support_Version VARCHAR(10),
       channel           VARCHAR(10),
       create_time       timestamp,
       update_time       timestamp
);
alter  table c_version_rule_config add constraint PK_c_version_rule_config_Id primary key (Id);
comment on table c_version_rule_config is '版本规则配置表';
comment on column c_version_rule_config.Id is '编号';
comment on column c_version_rule_config.min_support_Version is '最小支持版本';
comment on column c_version_rule_config.max_support_Version is '最大支持版本';
comment on column c_version_rule_config.channel is '渠道 1-表示android   2-表示ioS ';
comment on column c_version_rule_config.create_time is '创建时间';
comment on column c_version_rule_config.update_time is '修改时间';

ALTER TABLE c_customer add COLUMN Authentication_id INTEGER;
comment on column c_customer.Authentication_id is '认证ID';


ALTER TABLE c_login_history ADD COLUMN channel character varying(10);
COMMENT ON COLUMN c_login_history.channel IS '登录渠道：0-ios、1-web端 2android';


insert into c_authentication(id,password,channel,create_time,update_time,mobile)
select id,login_password,'0',create_time,update_time,mobile from c_customer;

update c_customer set authentication_id = id;




--------2015-03-18--------------
DROP TABLE f_reward_account_balance;
CREATE TABLE f_reward_account_balance ( id INTEGER NOT NULL, customer_id CHARACTER VARYING(30), reward_account_balance NUMERIC(18,2), reward_income_balance NUMERIC(18,2), reward_expend_balance NUMERIC(18,2), create_time TIMESTAMP(6) WITHOUT TIME ZONE, update_time TIMESTAMP(6) WITHOUT TIME ZONE, CONSTRAINT pk_f_reward_nce_id PRIMARY KEY (id) );
COMMENT ON TABLE f_reward_account_balance IS '奖励账户余额';
COMMENT ON COLUMN f_reward_account_balance.id IS '编号';
COMMENT ON COLUMN f_reward_account_balance.customer_id IS '客户ID';
COMMENT ON COLUMN f_reward_account_balance.reward_account_balance IS '奖励账户余额';
COMMENT ON COLUMN f_reward_account_balance.reward_income_balance IS '奖励收入金额';
COMMENT ON COLUMN f_reward_account_balance.reward_expend_balance IS '奖励支出兑换金额';
COMMENT ON COLUMN f_reward_account_balance.create_time IS '创建时间';
COMMENT ON COLUMN f_reward_account_balance.update_time IS '修改时间';

DROP TABLE f_reward_account_details;
CREATE TABLE f_reward_account_details ( id INTEGER NOT NULL, customer_id CHARACTER VARYING(30), reward_sequence CHARACTER VARYING(20), activity_type CHARACTER VARYING(10), reward_type CHARACTER VARYING(10), reward_amount_balance BIGINT, income_expend_balance NUMERIC(18,2), fund_flow_type CHARACTER VARYING(10), create_time TIMESTAMP(6) WITHOUT TIME ZONE, update_time TIMESTAMP(6) WITHOUT TIME ZONE, CONSTRAINT pk_f_reward_ils_id PRIMARY KEY (id) );
COMMENT ON TABLE f_reward_account_details IS '奖励账户余额明细';
COMMENT ON COLUMN f_reward_account_details.id IS '编号';
COMMENT ON COLUMN f_reward_account_details.customer_id IS '客户编号';
COMMENT ON COLUMN f_reward_account_details.reward_sequence IS '奖励流水 以R开头年月日时分秒+递增数字或IDR2015031709531';
COMMENT ON COLUMN f_reward_account_details.activity_type IS '活动类型 0-签到奖励，1-首次购买奖励，2-红包，3-推荐好友';
COMMENT ON COLUMN f_reward_account_details.reward_type IS '奖励类型';
COMMENT ON COLUMN f_reward_account_details.income_expend_balance IS '奖励支出收入金额';
COMMENT ON COLUMN f_reward_account_details.fund_flow_type IS '资金流向 0-奖励收入，1-奖励支出(兑换)';
COMMENT ON COLUMN f_reward_account_details.create_time IS '创建时间';
COMMENT ON COLUMN f_reward_account_details.update_time IS '修改时间';


--奖励账户余额数据迁移
insert into f_reward_account_balance select nextval('src'),a.customer_id,a.holdSum,a.getSum,a.frozenSum,now() from (
select customer_id, sum(get_money - frozen_money) holdSum, sum(get_money) getSum,  sum(frozen_money) frozenSum from f_reward_count group by customer_id) a;

--奖励账户明细数据迁移
insert into f_reward_account_details
select nextval('src'), a.customer_id, 'R' || to_char(current_timestamp, 'YYYYMMDDHH24MISS') || nextval('src'),a.scene,a.reward_type,a.reward_amt, a.money, a.fundType,a.create_time from
(select customer_id,scene,reward_type,reward_amt, money, '0' fundType,create_time from f_reward_flow where operator_type = 1
union
select customer_id,scene,reward_type,reward_amt, money, '1' fundType,create_time from f_reward_flow where operator_type = 2 and status = 7) a;



/*权限脚本*/
/*
GRANT ALL ON TABLE c_Authentication TO uat_user;
GRANT ALL ON TABLE c_version_rule_config TO uat_user;
GRANT ALL ON TABLE f_reward_account_balance TO uat_user;
GRANT ALL ON TABLE f_reward_account_details TO uat_user;
 */




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
create sequence SEQ_c_Authentication;

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
alter  table c_version_rule_config
       add constraint PK_c_version_rule_config_Id primary key (Id);
comment on table c_version_rule_config is '版本规则配置表';
comment on column c_version_rule_config.Id is '编号';
comment on column c_version_rule_config.min_support_Version is '最小支持版本';
comment on column c_version_rule_config.max_support_Version is '最大支持版本';
comment on column c_version_rule_config.channel is '渠道 1-表示android   2-表示ioS ';
comment on column c_version_rule_config.create_time is '创建时间';
comment on column c_version_rule_config.update_time is '修改时间';
create sequence SEQ_c_version_rule_config;

ALTER TABLE c_customer add COLUMN Authentication_id INTEGER;
comment on column c_customer.Authentication_id is '认证ID';


ALTER TABLE c_login_history ADD COLUMN channel character varying(10);
COMMENT ON COLUMN c_login_history.channel IS '登录渠道：'0-ios、1-web端 2android';


insert into c_authentication(id,password,channel,create_time,update_time,mobile)
select id,login_password,'0',create_time,update_time,mobile from c_customer;

update c_customer set authentication_id = id;




--------2015-03-18--------------

--奖励账户余额
create table  f_reward_account_balance
(
       id                INTEGER not null,
       customer_id       INTEGER,
       reward_account_balance INTEGER,
       reward_income_balance INTEGER,
       reward_expend_balance INTEGER,
       create_time       timestamp,
       update_time       timestamp
);
alter  table f_reward_account_balance
       add constraint PK_f_reward_nce_id primary key (id);
comment on table f_reward_account_balance is '奖励账户余额';
comment on column f_reward_account_balance.id is '编号';
comment on column f_reward_account_balance.customer_id is '客户ID';
comment on column f_reward_account_balance.reward_account_balance is '奖励账户余额';
comment on column f_reward_account_balance.reward_income_balance is '奖励收入金额';
comment on column f_reward_account_balance.reward_expend_balance is '奖励支出兑换金额';
comment on column f_reward_account_balance.create_time is '创建时间';
comment on column f_reward_account_balance.update_time is '修改时间';


--奖励账户余额明细
create table  f_reward_account_details
(
       Id                INTEGER not null,
       customer_id       INTEGER,
       reward_sequence   VARCHAR(20),
       activity_type     VARCHAR(10),
       reward_type       VARCHAR(10),
       reward_amount_balance INTEGER,
       income_expend_balance INTEGER,
       fund_flow_type    VARCHAR(10),
       create_time       timestamp,
       update_time       timestamp
);
alter  table f_reward_account_details
       add constraint PK_f_reward_ils_Id primary key (Id);
comment on table f_reward_account_details is '奖励账户余额明细';
comment on column f_reward_account_details.Id is '编号';
comment on column f_reward_account_details.customer_id is '客户编号';
comment on column f_reward_account_details.reward_sequence is '奖励流水 以R开头年月日时分秒+递增数字或IDR2015031709531';
comment on column f_reward_account_details.activity_type is '活动类型 0-签到奖励，1-首次购买奖励，2-红包，3-推荐好友';
comment on column f_reward_account_details.reward_type is '奖励类型';
comment on column f_reward_account_details.reward_amount_balance is '奖励数量金额 红包奖励金额、签到奖励金豆数量';
comment on column f_reward_account_details.income_expend_balance is '奖励支出收入金额';
comment on column f_reward_account_details.fund_flow_type is '资金流向 0-奖励收入，1-奖励支出(兑换)';
comment on column f_reward_account_details.create_time is '创建时间';
comment on column f_reward_account_details.update_time is '修改时间';
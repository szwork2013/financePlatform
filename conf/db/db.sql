/*
Navicat PGSQL Data Transfer

Source Server         : postgres_dev
Source Server Version : 90305
Source Host           : 192.168.1.95:5432
Source Database       : sunlightsdev
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90305
File Encoding         : 65001

Date: 2014-11-05 16:27:09
*/


-- ----------------------------
-- Sequence structure for cust_seq
-- ----------------------------
DROP SEQUENCE "public"."cust_seq";
CREATE SEQUENCE "public"."cust_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9999999999
 START 19
 CACHE 1
 CYCLE;
COMMENT ON SEQUENCE "public"."cust_seq" IS 
'客户号序列';
SELECT setval('"public"."cust_seq"', 19, true);

-- ----------------------------
-- Sequence structure for hibernate_sequence
-- ----------------------------
DROP SEQUENCE "public"."hibernate_sequence";
CREATE SEQUENCE "public"."hibernate_sequence"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 14
 CACHE 1;
SELECT setval('"public"."hibernate_sequence"', 14, true);

-- ----------------------------
-- Sequence structure for seq_base_model
-- ----------------------------
DROP SEQUENCE "public"."seq_base_model";
CREATE SEQUENCE "public"."seq_base_model"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9999999999
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for src
-- ----------------------------
DROP SEQUENCE "public"."src";
CREATE SEQUENCE "public"."src"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 270
 CACHE 1;
SELECT setval('"public"."src"', 270, true);

-- ----------------------------
-- Sequence structure for trade_seq
-- ----------------------------
DROP SEQUENCE "public"."trade_seq";
CREATE SEQUENCE "public"."trade_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9999
 START 1
 CACHE 1
 CYCLE;
COMMENT ON SEQUENCE "public"."trade_seq" IS 
'交易流水号';

-- ----------------------------
-- Table structure for c_bank
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_bank";
CREATE TABLE "public"."c_bank" (
"id" int8 NOT NULL,
"bank_code" varchar(40) COLLATE "default",
"bank_name" varchar(50) COLLATE "default",
"created_datetime" timestamp(6),
"en_name" varchar(50) COLLATE "default",
"status" varchar(1) COLLATE "default",
"updated_datetime" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."c_bank"."bank_code" IS '银行编码';
COMMENT ON COLUMN "public"."c_bank"."bank_name" IS '银行名称';
COMMENT ON COLUMN "public"."c_bank"."en_name" IS '英文名';
COMMENT ON COLUMN "public"."c_bank"."status" IS '状态';

-- ----------------------------
-- Table structure for c_bank_card
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_bank_card";
CREATE TABLE "public"."c_bank_card" (
"id" int8 NOT NULL,
"bank_card_no" varchar(40) COLLATE "default",
"bank_code" varchar(40) COLLATE "default",
"status" varchar(1) COLLATE "default",
"bank_type" varchar(1) COLLATE "default",
"created_datetime" timestamp(6),
"customer_id" varchar(30) COLLATE "default",
"update_datetime" timestamp(6),
"bank_id" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for c_customer
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_customer";
CREATE TABLE "public"."c_customer" (
"id" int8 NOT NULL,
"created_by" varchar(30) COLLATE "default",
"created_datetime" timestamp(6),
"customer_id" varchar(30) COLLATE "default",
"customer_type" varchar(1) COLLATE "default",
"device_no" varchar(50) COLLATE "default",
"email" varchar(50) COLLATE "default",
"identity_number" varchar(30) COLLATE "default",
"identity_typer" varchar(1) COLLATE "default",
"login_id" varchar(20) COLLATE "default",
"login_password" varchar(40) COLLATE "default",
"mobile" varchar(11) COLLATE "default",
"nick_name" varchar(20) COLLATE "default",
"pic_way" varchar(40) COLLATE "default",
"property" varchar(1) COLLATE "default",
"qq" varchar(20) COLLATE "default",
"real_name" varchar(20) COLLATE "default",
"referral_code" varchar(10) COLLATE "default",
"reg_channel" varchar(1) COLLATE "default",
"reg_way" varchar(1) COLLATE "default",
"status" varchar(1) COLLATE "default",
"updated_by" varchar(30) COLLATE "default",
"updated_datetime" timestamp(6),
"weibo" varchar(30) COLLATE "default",
"weixin" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."c_customer"."created_by" IS '创建人';
COMMENT ON COLUMN "public"."c_customer"."created_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."c_customer"."customer_id" IS '客户号';
COMMENT ON COLUMN "public"."c_customer"."customer_type" IS '客户类型';
COMMENT ON COLUMN "public"."c_customer"."device_no" IS '注册设备号';
COMMENT ON COLUMN "public"."c_customer"."email" IS '绑定邮箱';
COMMENT ON COLUMN "public"."c_customer"."identity_number" IS '证件号码';
COMMENT ON COLUMN "public"."c_customer"."identity_typer" IS '证件类型';
COMMENT ON COLUMN "public"."c_customer"."login_id" IS '登陆号';
COMMENT ON COLUMN "public"."c_customer"."login_password" IS '登陆密码';
COMMENT ON COLUMN "public"."c_customer"."mobile" IS '绑定手机';
COMMENT ON COLUMN "public"."c_customer"."nick_name" IS '昵称';
COMMENT ON COLUMN "public"."c_customer"."pic_way" IS '用户图像存放路径';
COMMENT ON COLUMN "public"."c_customer"."property" IS '用户属性';
COMMENT ON COLUMN "public"."c_customer"."qq" IS '绑定QQ';
COMMENT ON COLUMN "public"."c_customer"."real_name" IS '真实姓名';
COMMENT ON COLUMN "public"."c_customer"."referral_code" IS '推荐码';
COMMENT ON COLUMN "public"."c_customer"."reg_channel" IS '注册渠道';
COMMENT ON COLUMN "public"."c_customer"."reg_way" IS '注册方式';
COMMENT ON COLUMN "public"."c_customer"."status" IS '用户状态';
COMMENT ON COLUMN "public"."c_customer"."weibo" IS '绑定微博号';
COMMENT ON COLUMN "public"."c_customer"."weixin" IS '绑定微信号';

-- ----------------------------
-- Table structure for c_customer_gesture
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_customer_gesture";
CREATE TABLE "public"."c_customer_gesture" (
"id" int8 NOT NULL,
"created_datetime" timestamp(6),
"device_no" varchar(40) COLLATE "default",
"gesture_password" varchar(40) COLLATE "default",
"status" varchar(1) COLLATE "default",
"updated_datetime" timestamp(6),
"customer_id" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."c_customer_gesture"."created_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."c_customer_gesture"."device_no" IS '设备号';
COMMENT ON COLUMN "public"."c_customer_gesture"."gesture_password" IS '手势密码';
COMMENT ON COLUMN "public"."c_customer_gesture"."status" IS 'Y失效 N有效';
COMMENT ON COLUMN "public"."c_customer_gesture"."updated_datetime" IS '更新时间';
COMMENT ON COLUMN "public"."c_customer_gesture"."customer_id" IS '客户号';

-- ----------------------------
-- Table structure for c_customer_session
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_customer_session";
CREATE TABLE "public"."c_customer_session" (
"id" int8 NOT NULL,
"client_address" varchar(40) COLLATE "default",
"created_datetime" timestamp(6),
"device_name" varchar(40) COLLATE "default",
"device_no" varchar(40) COLLATE "default",
"status" varchar(1) COLLATE "default",
"token" varchar(400) COLLATE "default",
"updated_datetime" timestamp(6),
"customer_id" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."c_customer_session"."client_address" IS '客户端Ip地址';
COMMENT ON COLUMN "public"."c_customer_session"."device_name" IS '设备名称';
COMMENT ON COLUMN "public"."c_customer_session"."device_no" IS '设备号';
COMMENT ON COLUMN "public"."c_customer_session"."status" IS 'Y失效 N有效';
COMMENT ON COLUMN "public"."c_customer_session"."customer_id" IS '客户号';

-- ----------------------------
-- Table structure for c_customer_verify_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_customer_verify_code";
CREATE TABLE "public"."c_customer_verify_code" (
"id" int8 NOT NULL,
"created_datetime" timestamp(6),
"device_no" varchar(40) COLLATE "default",
"mobile" varchar(11) COLLATE "default",
"status" varchar(1) COLLATE "default",
"updated_datetime" timestamp(6),
"verify_code" varchar(40) COLLATE "default",
"verify_type" varchar(20) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."c_customer_verify_code"."device_no" IS '设备号';
COMMENT ON COLUMN "public"."c_customer_verify_code"."mobile" IS '手机号';
COMMENT ON COLUMN "public"."c_customer_verify_code"."status" IS 'Y失效 N有效';
COMMENT ON COLUMN "public"."c_customer_verify_code"."verify_code" IS '验证码';
COMMENT ON COLUMN "public"."c_customer_verify_code"."verify_type" IS '类型';

-- ----------------------------
-- Table structure for c_feedbac.sql
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_feedback";
CREATE TABLE "public"."c_feedback" (
"id" int8 NOT NULL,
"customer_id" varchar(30) COLLATE "default",
"context" text COLLATE "default",
"mobile" varchar(20) COLLATE "default",
"remark" text COLLATE "default",
"status" varchar(50) COLLATE "default",
"device_no" varchar(50) COLLATE "default",
"create_time" timestamp(6),
"update_time" timestamp(6),
"update_by" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for c_open_account_pact
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_open_account_pact";
CREATE TABLE "public"."c_open_account_pact" (
"agreement_no" varchar(20) COLLATE "default" NOT NULL,
"sign_channel" varchar(10) COLLATE "default",
"agreement_start_date" timestamp(6),
"agreement_end_date" timestamp(6),
"agreement_effect_date" timestamp(6),
"agreement_expiry_date" timestamp(6),
"agreement_status" varchar(1) COLLATE "default",
"sale_property" varchar(1) COLLATE "default",
"user_attribution" varchar(1) COLLATE "default",
"agreement_name" varchar(40) COLLATE "default",
"agreement_file_type" varchar(10) COLLATE "default",
"file_name" varchar(100) COLLATE "default",
"file_path" varchar(200) COLLATE "default",
"status" varchar(1) COLLATE "default",
"create_date" timestamp(6),
"update_date" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for code_mstr
-- ----------------------------
DROP TABLE IF EXISTS "public"."code_mstr";
CREATE TABLE "public"."code_mstr" (
"id" int8 NOT NULL,
"code_cat" varchar(50) COLLATE "default",
"code_abbr" varchar(50) COLLATE "default",
"code_seq" int4,
"created_by" varchar(255) COLLATE "default",
"created_time" timestamp(6),
"status" varchar(1) COLLATE "default",
"magic" varchar(255) COLLATE "default",
"remarks" varchar(255) COLLATE "default",
"parent_code_cat" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."code_mstr"."code_cat" IS '代码类别';
COMMENT ON COLUMN "public"."code_mstr"."code_abbr" IS '业务状态';
COMMENT ON COLUMN "public"."code_mstr"."code_seq" IS '顺序号';
COMMENT ON COLUMN "public"."code_mstr"."status" IS '有效状态';
COMMENT ON COLUMN "public"."code_mstr"."parent_code_cat" IS '父代码';

-- ----------------------------
-- Table structure for f_acct_chang_flow
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_acct_chang_flow";
CREATE TABLE "public"."f_acct_chang_flow" (
"id" int8 NOT NULL,
"prd_code" varchar(8) COLLATE "default",
"trade_no" varchar(20) COLLATE "default",
"subject_no" varchar(6) COLLATE "default",
"amount" numeric(18,4),
"create_time" timestamp(6),
"cust_id" char(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."f_acct_chang_flow" IS '账户变动流水表';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."id" IS '账户变动流水ID';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."prd_code" IS '产品编码';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."trade_no" IS '交易流水号';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."subject_no" IS '科目号';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."amount" IS '变动金额';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."cust_id" IS '客户号';

-- ----------------------------
-- Table structure for f_basic_account
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_basic_account";
CREATE TABLE "public"."f_basic_account" (
"id" int8 NOT NULL,
"account_no" varchar(30) COLLATE "default",
"status" char(1) COLLATE "default",
"balance" numeric(18,4),
"create_time" timestamp(6),
"update_time" timestamp(6),
"delete_time" timestamp(6),
"trade_password" varchar(50) COLLATE "default",
"cust_id" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."f_basic_account" IS '基本账户';
COMMENT ON COLUMN "public"."f_basic_account"."id" IS '基本账户Id';
COMMENT ON COLUMN "public"."f_basic_account"."account_no" IS '基本账户号';
COMMENT ON COLUMN "public"."f_basic_account"."status" IS 'N表示正常';
COMMENT ON COLUMN "public"."f_basic_account"."balance" IS '余额';
COMMENT ON COLUMN "public"."f_basic_account"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."f_basic_account"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."f_basic_account"."delete_time" IS '删除时间';
COMMENT ON COLUMN "public"."f_basic_account"."trade_password" IS '交易密码';
COMMENT ON COLUMN "public"."f_basic_account"."cust_id" IS '客户号';



-- ----------------------------
-- Table structure for f_fund_Agreement
-- ----------------------------
CREATE TABLE f_fund_Agreement
(
  id bigint NOT NULL,
  customer_id character varying(30),
  company_code character varying(20),
  create_time timestamp without time zone,
  update_time timestamp without time zone,
  CONSTRAINT c_bank_card_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

-- ----------------------------
-- Table structure for f_holdcapital
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_holdcapital";
CREATE TABLE "public"."f_holdcapital" (
"id" int8 NOT NULL,
"create_time" timestamp(6),
"cust_id" varchar(255) COLLATE "default",
"delete_time" timestamp(6),
"hold_capital" numeric(18,4),
"product_code" varchar(8) COLLATE "default",
"product_name" varchar(100) COLLATE "default",
"settle_date" timestamp(6),
"status" varchar(1) COLLATE "default",
"total_profit" numeric(18,4),
"trade_amount" numeric(18,4),
"update_time" timestamp(6),
"yesterday_profit" numeric(18,4),
"product_type" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."f_holdcapital"."cust_id" IS '客户号';
COMMENT ON COLUMN "public"."f_holdcapital"."hold_capital" IS '持有资产';
COMMENT ON COLUMN "public"."f_holdcapital"."settle_date" IS '结算日期';
COMMENT ON COLUMN "public"."f_holdcapital"."total_profit" IS '累计收益';
COMMENT ON COLUMN "public"."f_holdcapital"."trade_amount" IS '申赎资产';
COMMENT ON COLUMN "public"."f_holdcapital"."yesterday_profit" IS '昨日收益';

-- ----------------------------
-- Table structure for f_sub_account
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_sub_account";
CREATE TABLE "public"."f_sub_account" (
"id" int8 NOT NULL,
"sub_account" varchar(10) COLLATE "default",
"basic_account" int8,
"status" char(1) COLLATE "default",
"balance" numeric(18,4),
"profit" numeric(18,4),
"yesterday_profit" numeric(18,4),
"create_time" timestamp(6),
"update_time" timestamp(6),
"delete_time" timestamp(6),
"cust_id" char(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."f_sub_account" IS '子账户';
COMMENT ON COLUMN "public"."f_sub_account"."id" IS '子账户ID';
COMMENT ON COLUMN "public"."f_sub_account"."sub_account" IS '子账户号';
COMMENT ON COLUMN "public"."f_sub_account"."basic_account" IS '基本账户号';
COMMENT ON COLUMN "public"."f_sub_account"."status" IS '子账户状态';
COMMENT ON COLUMN "public"."f_sub_account"."balance" IS '余额';
COMMENT ON COLUMN "public"."f_sub_account"."profit" IS '累计收益';
COMMENT ON COLUMN "public"."f_sub_account"."yesterday_profit" IS '昨日收益';
COMMENT ON COLUMN "public"."f_sub_account"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."f_sub_account"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."f_sub_account"."delete_time" IS '删除时间';
COMMENT ON COLUMN "public"."f_sub_account"."cust_id" IS '客户号';

-- ----------------------------
-- Table structure for f_subject
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_subject";
CREATE TABLE "public"."f_subject" (
"id" int8 NOT NULL,
"NO" varchar(6) COLLATE "default",
"description" varchar(20) COLLATE "default",
"dc_flag" char(1) COLLATE "default",
"create_time" timestamp(6),
"update_time" timestamp(6),
"delete_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."f_subject" IS '科目表';
COMMENT ON COLUMN "public"."f_subject"."id" IS '科目ID';
COMMENT ON COLUMN "public"."f_subject"."NO" IS '科目号';
COMMENT ON COLUMN "public"."f_subject"."description" IS '科目说明';
COMMENT ON COLUMN "public"."f_subject"."dc_flag" IS '借贷标志  增加资金记贷方 C  较少资金记借方 D';
COMMENT ON COLUMN "public"."f_subject"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."f_subject"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."f_subject"."delete_time" IS '删除时间';

-- ----------------------------
-- Table structure for loggingevent
-- ----------------------------
DROP TABLE IF EXISTS "public"."loggingevent";
CREATE TABLE "public"."loggingevent" (
"log_event_id" int8 NOT NULL,
"log_event_arg0" varchar(10) COLLATE "default",
"log_event_arg1" varchar(10) COLLATE "default",
"log_event_arg2" varchar(10) COLLATE "default",
"log_event_arg3" varchar(10) COLLATE "default",
"log_event_caller_class" varchar(10) COLLATE "default",
"log_event_caller_filename" varchar(10) COLLATE "default",
"log_event_caller_line" varchar(10) COLLATE "default",
"log_event_caller_method" varchar(10) COLLATE "default",
"log_event_format_msg" varchar(10) COLLATE "default",
"log_event_level" varchar(10) COLLATE "default",
"log_event_logger_name" varchar(10) COLLATE "default",
"log_event_ref_flag" int4,
"log_event_thread_name" varchar(10) COLLATE "default",
"log_event_timestmp" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for loggingeventexception
-- ----------------------------
DROP TABLE IF EXISTS "public"."loggingeventexception";
CREATE TABLE "public"."loggingeventexception" (
"log_event_id" int8 NOT NULL,
"log_event_i" int4,
"log_event_trace_line" varchar(10) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for loggingeventproperty
-- ----------------------------
DROP TABLE IF EXISTS "public"."loggingeventproperty";
CREATE TABLE "public"."loggingeventproperty" (
"log_event_id" int8 NOT NULL,
"log_mapped_key" varchar(10) COLLATE "default",
"log_mapped_value" varchar(10) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for login_history
-- ----------------------------
DROP TABLE IF EXISTS "public"."login_history";
CREATE TABLE "public"."login_history" (
"id" int8 NOT NULL,
"created_datetime" timestamp(6),
"device_no" varchar(40) COLLATE "default",
"gesture_ind" varchar(1) COLLATE "default",
"log_num" int8,
"login_datetime" timestamp(6),
"logout_datetime" timestamp(6),
"pwd_ind" varchar(1) COLLATE "default",
"social_ind" varchar(1) COLLATE "default",
"success_ind" varchar(1) COLLATE "default",
"updated_datetime" timestamp(6),
"customer_id" varchar(30) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."login_history"."device_no" IS '设备号';
COMMENT ON COLUMN "public"."login_history"."gesture_ind" IS 'Y手势登录 ';
COMMENT ON COLUMN "public"."login_history"."log_num" IS '登录失败次数';
COMMENT ON COLUMN "public"."login_history"."login_datetime" IS '登录时间';
COMMENT ON COLUMN "public"."login_history"."logout_datetime" IS '登出时间';
COMMENT ON COLUMN "public"."login_history"."pwd_ind" IS 'Y密码登录';
COMMENT ON COLUMN "public"."login_history"."success_ind" IS '成功登录标志';
COMMENT ON COLUMN "public"."login_history"."customer_id" IS '客户号';

-- ----------------------------
-- Table structure for m_logging_event
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_logging_event";
CREATE TABLE "public"."m_logging_event" (
"log_event_id" int8 NOT NULL,
"log_event_arg0" varchar(10) COLLATE "default",
"log_event_arg1" varchar(10) COLLATE "default",
"log_event_arg2" varchar(10) COLLATE "default",
"log_event_arg3" varchar(10) COLLATE "default",
"log_event_caller_class" varchar(10) COLLATE "default",
"log_event_caller_filename" varchar(10) COLLATE "default",
"log_event_caller_line" varchar(10) COLLATE "default",
"log_event_caller_method" varchar(10) COLLATE "default",
"log_event_format_msg" varchar(10) COLLATE "default",
"log_event_level" varchar(10) COLLATE "default",
"log_event_logger_name" varchar(10) COLLATE "default",
"log_event_ref_flag" int4,
"log_event_thread_name" varchar(10) COLLATE "default",
"log_event_timestmp" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for m_logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_logging_event_exception";
CREATE TABLE "public"."m_logging_event_exception" (
"log_event_id" int8 NOT NULL,
"log_event_i" int4,
"log_event_trace_line" varchar(10) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for m_logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_logging_event_property";
CREATE TABLE "public"."m_logging_event_property" (
"log_event_id" int8 NOT NULL,
"log_mapped_key" varchar(10) COLLATE "default",
"log_mapped_value" varchar(10) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for p_fund
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_fund";
CREATE TABLE "public"."p_fund" (
"id" int8 NOT NULL,
"min_apply_amount" numeric(18,4),
"lowest_redemption" int8,
"one_year_profit" numeric(18,4),
"million_of_profit" numeric(18,4),
"one_week_profit" numeric(18,4),
"nav_date" timestamp(6),
"is_apply" varchar(50) COLLATE "default",
"is_redemption" varchar(50) COLLATE "default",
"product_status" varchar(50) COLLATE "default",
"deleted" varchar(50) COLLATE "default",
"create_time" timestamp(6),
"update_time" timestamp(6),
"update_by" varchar(10) COLLATE "default",
"create_by" varchar(10) COLLATE "default",
"company_name" varchar(10) COLLATE "default",
"chi_name" varchar(100) COLLATE "default",
"chi_name_abbr" varchar(50) COLLATE "default",
"eng_name" varchar(100) COLLATE "default",
"eng_name_abbr" varchar(50) COLLATE "default",
"secu_abbr" varchar(20) COLLATE "default",
"fund_scale" numeric,
"fund_type" varchar(50) COLLATE "default",
"invest_period" varchar(50) COLLATE "default",
"charge" numeric,
"to_account_type" varchar(50) COLLATE "default",
"supplier_id" int8,
"risk_level" varchar(50) COLLATE "default",
"fund_code" varchar(10) COLLATE "default",
"init_buyed_count" int8,
"one_month_buyed_count" int8
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for p_fund_history
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_fund_history";
CREATE TABLE "public"."p_fund_history" (
"id" int8 NOT NULL,
"min_apply_amount" numeric(18,4),
"lowest_redemption" int8,
"one_year_profit" numeric(18,4),
"million_of_profit" numeric(18,4),
"one_week_profit" numeric(18,4),
"nav_date" timestamp(6),
"is_apply" varchar(50) COLLATE "default",
"is_redemption" varchar(50) COLLATE "default",
"product_status" int4,
"deleted" bool,
"create_time" timestamp(6),
"update_time" timestamp(6),
"update_by" varchar(10) COLLATE "default",
"create_by" varchar(10) COLLATE "default",
"company_name" varchar(10) COLLATE "default",
"chi_name" varchar(100) COLLATE "default",
"chi_name_abbr" varchar(50) COLLATE "default",
"eng_name" varchar(100) COLLATE "default",
"eng_name_abbr" varchar(50) COLLATE "default",
"secu_abbr" varchar(20) COLLATE "default",
"fund_scale" numeric,
"fund_type" varchar(50) COLLATE "default",
"invest_period" varchar(50) COLLATE "default",
"charge" numeric,
"to_account_type" varchar(50) COLLATE "default",
"supplier_id" int8,
"risk_level" varchar(50) COLLATE "default",
"fund_code" varchar(10) COLLATE "default",
"init_buyed_count" int8,
"one_month_buyed_count" int8,
"created_by" varchar(30) COLLATE "default",
"onr_month_buyed_count" int8,
"suppiler_id" int8,
"updated_by" varchar(30) COLLATE "default",
"updated_time" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for p_prd_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_prd_type";
CREATE TABLE "public"."p_prd_type" (
"id" int8 NOT NULL,
"code" varchar(8) COLLATE "default",
"name" varchar(60) COLLATE "default",
"create_time" timestamp(6),
"update_time" timestamp(6),
"delete_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."p_prd_type" IS '产品类型表';
COMMENT ON COLUMN "public"."p_prd_type"."id" IS '主键';
COMMENT ON COLUMN "public"."p_prd_type"."code" IS '产品类型编码';
COMMENT ON COLUMN "public"."p_prd_type"."name" IS '产品类型名称';
COMMENT ON COLUMN "public"."p_prd_type"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."p_prd_type"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."p_prd_type"."delete_time" IS '删除时间';

-- ----------------------------
-- Table structure for p_product
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_product";
CREATE TABLE "public"."p_product" (
"id" int8 NOT NULL,
"created_by" varchar(30) COLLATE "default",
"created_datetime" timestamp(6),
"defunct_ind" varchar(1) COLLATE "default",
"updated_by" varchar(30) COLLATE "default",
"updated_datetime" timestamp(6),
"isin" varchar(20) COLLATE "default",
"xgrq" timestamp(6),
"chi_name" varchar(200) COLLATE "default",
"chi_name_abbr" varchar(100) COLLATE "default",
"chi_spelling" varchar(10) COLLATE "default",
"company_code" int4,
"eng_name" varchar(200) COLLATE "default",
"eng_name_abbr" varchar(50) COLLATE "default",
"inner_code" varchar(10) COLLATE "default",
"listed_date" timestamp(6),
"listed_sector" int4,
"listed_state" int4,
"secu_abbr" varchar(20) COLLATE "default",
"secu_category" int4,
"secu_code" varchar(10) COLLATE "default",
"secu_market" int4,
"delete_flag" varchar(1) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for p_product_manage
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_product_manage";
CREATE TABLE "public"."p_product_manage" (
"id" int8 NOT NULL,
"product_name" varchar(10) COLLATE "default",
"begin_time" timestamp(6),
"end_date" timestamp(6),
"create_time" timestamp(6),
"update_time" timestamp(6),
"product_desc" varchar(100) COLLATE "default",
"product_status" char(1) COLLATE "default",
"url" varchar(300) COLLATE "default",
"product_code" varchar(10) COLLATE "default",
"product_type" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for p_product_recommend
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_product_recommend";
CREATE TABLE "public"."p_product_recommend" (
"id" int8 NOT NULL,
"product_id" int8,
"product_name" varchar(100) COLLATE "default",
"recommend_type" varchar(8) COLLATE "default",
"product_code" varchar(8) COLLATE "default",
"begin_date" timestamp(6),
"end_date" timestamp(6),
"temp_stop_date" timestamp(6),
"priority_level" int4,
"recommend_flag" int4,
"recommend_desc" varchar(50) COLLATE "default",
"url" varchar(500) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS "public"."parameter";
CREATE TABLE "public"."parameter" (
"id" int8 NOT NULL,
"created_by" varchar(30) COLLATE "default",
"created_datetime" timestamp(6),
"defunct_ind" varchar(1) COLLATE "default",
"updated_by" varchar(30) COLLATE "default",
"updated_datetime" timestamp(6),
"description" varchar(255) COLLATE "default",
"name" varchar(50) COLLATE "default",
"value" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."parameter"."created_by" IS '创建人';
COMMENT ON COLUMN "public"."parameter"."created_datetime" IS '创建时间';
COMMENT ON COLUMN "public"."parameter"."defunct_ind" IS 'N有效 Y失效';
COMMENT ON COLUMN "public"."parameter"."updated_by" IS '更新人';
COMMENT ON COLUMN "public"."parameter"."updated_datetime" IS '更新时间';
COMMENT ON COLUMN "public"."parameter"."description" IS '描述';
COMMENT ON COLUMN "public"."parameter"."name" IS '参数名';
COMMENT ON COLUMN "public"."parameter"."value" IS '参数值';

-- ----------------------------
-- Table structure for prd_account_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."prd_account_config";
CREATE TABLE "public"."prd_account_config" (
"id" int8 NOT NULL,
"prd_type_code" varchar(8) COLLATE "default",
"sub_account" varchar(10) COLLATE "default",
"create_time" timestamp(6),
"update_time" timestamp(6),
"delete_time" timestamp(6)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."prd_account_config" IS '产品-账户关系配置表';
COMMENT ON COLUMN "public"."prd_account_config"."id" IS '主键Id';
COMMENT ON COLUMN "public"."prd_account_config"."prd_type_code" IS '产品类型编码';
COMMENT ON COLUMN "public"."prd_account_config"."sub_account" IS '子账户号';
COMMENT ON COLUMN "public"."prd_account_config"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."prd_account_config"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."prd_account_config"."delete_time" IS '删除时间';

-- ----------------------------
-- Table structure for productrec
-- ----------------------------
DROP TABLE IF EXISTS "public"."productrec";
CREATE TABLE "public"."productrec" (
"id" int8 NOT NULL,
"product_begin" timestamp(6),
"product_end" timestamp(6),
"product_level" int4,
"product_code" varchar(10) COLLATE "default",
"product_id" varchar(10) COLLATE "default",
"product_desc" varchar(200) COLLATE "default",
"product_flag" int4,
"product_type" varchar(10) COLLATE "default",
"product_temp_stop" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_blob_triggers";
CREATE TABLE "public"."qrtz_blob_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"blob_data" bytea
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_calendars";
CREATE TABLE "public"."qrtz_calendars" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"calendar_name" varchar(200) COLLATE "default" NOT NULL,
"calendar" bytea NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_cron_triggers";
CREATE TABLE "public"."qrtz_cron_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"cron_expression" varchar(120) COLLATE "default" NOT NULL,
"time_zone_id" varchar(80) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_fired_triggers";
CREATE TABLE "public"."qrtz_fired_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"entry_id" varchar(95) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"instance_name" varchar(200) COLLATE "default" NOT NULL,
"fired_time" int8 NOT NULL,
"priority" int4 NOT NULL,
"state" varchar(16) COLLATE "default" NOT NULL,
"job_name" varchar(200) COLLATE "default",
"job_group" varchar(200) COLLATE "default",
"is_nonconcurrent" bool,
"requests_recovery" bool
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_job_details";
CREATE TABLE "public"."qrtz_job_details" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"job_name" varchar(200) COLLATE "default" NOT NULL,
"job_group" varchar(200) COLLATE "default" NOT NULL,
"description" varchar(250) COLLATE "default",
"job_class_name" varchar(250) COLLATE "default" NOT NULL,
"is_durable" bool NOT NULL,
"is_nonconcurrent" bool NOT NULL,
"is_update_data" bool NOT NULL,
"requests_recovery" bool NOT NULL,
"job_data" bytea
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_locks";
CREATE TABLE "public"."qrtz_locks" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"lock_name" varchar(40) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_paused_trigger_grps";
CREATE TABLE "public"."qrtz_paused_trigger_grps" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_scheduler_state";
CREATE TABLE "public"."qrtz_scheduler_state" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"instance_name" varchar(200) COLLATE "default" NOT NULL,
"last_checkin_time" int8 NOT NULL,
"checkin_interval" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_simple_triggers";
CREATE TABLE "public"."qrtz_simple_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"repeat_count" int8 NOT NULL,
"repeat_interval" int8 NOT NULL,
"times_triggered" int8 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_simprop_triggers";
CREATE TABLE "public"."qrtz_simprop_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"str_prop_1" varchar(512) COLLATE "default",
"str_prop_2" varchar(512) COLLATE "default",
"str_prop_3" varchar(512) COLLATE "default",
"int_prop_1" int4,
"int_prop_2" int4,
"long_prop_1" int8,
"long_prop_2" int8,
"dec_prop_1" numeric(13,4),
"dec_prop_2" numeric(13,4),
"bool_prop_1" bool,
"bool_prop_2" bool
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS "public"."qrtz_triggers";
CREATE TABLE "public"."qrtz_triggers" (
"sched_name" varchar(120) COLLATE "default" NOT NULL,
"trigger_name" varchar(200) COLLATE "default" NOT NULL,
"trigger_group" varchar(200) COLLATE "default" NOT NULL,
"job_name" varchar(200) COLLATE "default" NOT NULL,
"job_group" varchar(200) COLLATE "default" NOT NULL,
"description" varchar(250) COLLATE "default",
"next_fire_time" int8,
"prev_fire_time" int8,
"priority" int4,
"trigger_state" varchar(16) COLLATE "default" NOT NULL,
"trigger_type" varchar(8) COLLATE "default" NOT NULL,
"start_time" int8 NOT NULL,
"end_time" int8,
"calendar_name" varchar(200) COLLATE "default",
"misfire_instr" int2,
"job_data" bytea
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for sms_message
-- ----------------------------
DROP TABLE IF EXISTS "public"."sms_message";
CREATE TABLE "public"."sms_message" (
"id" int8 NOT NULL,
"content" varchar(200) COLLATE "default",
"created_datetime" timestamp(6),
"mobile" varchar(11) COLLATE "default",
"rec_status" varchar(40) COLLATE "default",
"return_msg" varchar(200) COLLATE "default",
"smsid" varchar(40) COLLATE "default",
"updated_datetime" timestamp(6)
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS "public"."supplier";
CREATE TABLE "public"."supplier" (
"id" int8 NOT NULL,
"supplier_belong_addr" varchar(200) COLLATE "default",
"supplier_contact_call" varchar(15) COLLATE "default",
"supplier_corporate_property" varchar(20) COLLATE "default",
"supplier_customer_type" varchar(10) COLLATE "default",
"supplier_display_name" varchar(50) COLLATE "default",
"supplier_inner_code" int4,
"supplier_merchant_introduction" varchar(200) COLLATE "default",
"supplier_merchant_name" varchar(100) COLLATE "default",
"supplier_capital_paid" numeric(19,2),
"supplier_reg_addr" varchar(100) COLLATE "default",
"supplier_capital_reg" numeric(19,2),
"supplier_reg_city" varchar(10) COLLATE "default",
"supplier_reg_prov" varchar(10) COLLATE "default",
"supplier_type" int4,
"supplier_web_addr" varchar(50) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Table structure for t_trade
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_trade";
CREATE TABLE "public"."t_trade" (
"id" int8 NOT NULL,
"trade_no" varchar(20) COLLATE "default",
"fee" numeric(18,4),
"type" char(1) COLLATE "default",
"trade_status" char(1) COLLATE "default",
"confirm_status" char(1) COLLATE "default",
"trade_time" timestamp(6),
"create_time" timestamp(6),
"update_time" timestamp(6),
"delete_time" timestamp(6),
"cust_id" varchar(30) COLLATE "default",
"bank_card_no" varchar(40) COLLATE "default",
"bank_name" varchar(50) COLLATE "default",
"pay_status" varchar(1) COLLATE "default",
"trade_amount" numeric(18,4),
"product_code" varchar(8) COLLATE "default",
"product_name" varchar(100) COLLATE "default",
"product_price" numeric(18,4),
"quantity" int4,
"amount" int4,
"holdcapital_id" int8,
"pay_statu" varchar(1) COLLATE "default",
"prd_code" varchar(8) COLLATE "default",
"prd_pricr" numeric(18,4)
)
WITH (OIDS=FALSE)

;
COMMENT ON TABLE "public"."t_trade" IS '交易表';
COMMENT ON COLUMN "public"."t_trade"."id" IS '交易主键';
COMMENT ON COLUMN "public"."t_trade"."trade_no" IS '交易流水号';
COMMENT ON COLUMN "public"."t_trade"."fee" IS '手续费';
COMMENT ON COLUMN "public"."t_trade"."type" IS '1:申购 2:赎回 3:分红
';
COMMENT ON COLUMN "public"."t_trade"."trade_status" IS '1：存/取钱中、2：存/取钱成功、3：存/取失败【失败原因】
';
COMMENT ON COLUMN "public"."t_trade"."confirm_status" IS '0-不需发送；1-待确认；2-待确认；3-部分确认；4-确认完成；5-确认失败
';
COMMENT ON COLUMN "public"."t_trade"."trade_time" IS '下单时间';
COMMENT ON COLUMN "public"."t_trade"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."t_trade"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."t_trade"."delete_time" IS '删除时间';
COMMENT ON COLUMN "public"."t_trade"."cust_id" IS '客户号';
COMMENT ON COLUMN "public"."t_trade"."bank_card_no" IS '银行卡号';
COMMENT ON COLUMN "public"."t_trade"."bank_name" IS '银行名称';
COMMENT ON COLUMN "public"."t_trade"."trade_amount" IS '申赎金额';
COMMENT ON COLUMN "public"."t_trade"."product_code" IS '产品编码';
COMMENT ON COLUMN "public"."t_trade"."product_name" IS '产品名称';
COMMENT ON COLUMN "public"."t_trade"."product_price" IS '产品单价';
COMMENT ON COLUMN "public"."t_trade"."quantity" IS '数量';

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table c_bank
-- ----------------------------
ALTER TABLE "public"."c_bank" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_bank_card
-- ----------------------------
ALTER TABLE "public"."c_bank_card" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_customer
-- ----------------------------
ALTER TABLE "public"."c_customer" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_customer_gesture
-- ----------------------------
ALTER TABLE "public"."c_customer_gesture" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_customer_session
-- ----------------------------
ALTER TABLE "public"."c_customer_session" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_customer_verify_code
-- ----------------------------
ALTER TABLE "public"."c_customer_verify_code" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_feedbac.sql
-- ----------------------------
ALTER TABLE "public"."c_feedback" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_open_account_pact
-- ----------------------------
ALTER TABLE "public"."c_open_account_pact" ADD PRIMARY KEY ("agreement_no");

-- ----------------------------
-- Primary Key structure for table code_mstr
-- ----------------------------
ALTER TABLE "public"."code_mstr" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table f_acct_chang_flow
-- ----------------------------
ALTER TABLE "public"."f_acct_chang_flow" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table f_basic_account
-- ----------------------------
ALTER TABLE "public"."f_basic_account" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table f_holdcapital
-- ----------------------------
ALTER TABLE "public"."f_holdcapital" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table f_sub_account
-- ----------------------------
ALTER TABLE "public"."f_sub_account" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table f_subject
-- ----------------------------
ALTER TABLE "public"."f_subject" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table loggingevent
-- ----------------------------
ALTER TABLE "public"."loggingevent" ADD PRIMARY KEY ("log_event_id");

-- ----------------------------
-- Primary Key structure for table loggingeventexception
-- ----------------------------
ALTER TABLE "public"."loggingeventexception" ADD PRIMARY KEY ("log_event_id");

-- ----------------------------
-- Primary Key structure for table loggingeventproperty
-- ----------------------------
ALTER TABLE "public"."loggingeventproperty" ADD PRIMARY KEY ("log_event_id");

-- ----------------------------
-- Primary Key structure for table login_history
-- ----------------------------
ALTER TABLE "public"."login_history" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table m_logging_event
-- ----------------------------
ALTER TABLE "public"."m_logging_event" ADD PRIMARY KEY ("log_event_id");

-- ----------------------------
-- Primary Key structure for table m_logging_event_exception
-- ----------------------------
ALTER TABLE "public"."m_logging_event_exception" ADD PRIMARY KEY ("log_event_id");

-- ----------------------------
-- Primary Key structure for table m_logging_event_property
-- ----------------------------
ALTER TABLE "public"."m_logging_event_property" ADD PRIMARY KEY ("log_event_id");

-- ----------------------------
-- Primary Key structure for table p_fund
-- ----------------------------
ALTER TABLE "public"."p_fund" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table p_fund_history
-- ----------------------------
ALTER TABLE "public"."p_fund_history" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table p_prd_type
-- ----------------------------
ALTER TABLE "public"."p_prd_type" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table p_product
-- ----------------------------
ALTER TABLE "public"."p_product" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table p_product_manage
-- ----------------------------
ALTER TABLE "public"."p_product_manage" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table p_product_recommend
-- ----------------------------
ALTER TABLE "public"."p_product_recommend" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table parameter
-- ----------------------------
ALTER TABLE "public"."parameter" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table prd_account_config
-- ----------------------------
ALTER TABLE "public"."prd_account_config" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table productrec
-- ----------------------------
ALTER TABLE "public"."productrec" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table qrtz_blob_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_blob_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Primary Key structure for table qrtz_calendars
-- ----------------------------
ALTER TABLE "public"."qrtz_calendars" ADD PRIMARY KEY ("sched_name", "calendar_name");

-- ----------------------------
-- Primary Key structure for table qrtz_cron_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_cron_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Indexes structure for table qrtz_fired_triggers
-- ----------------------------
CREATE INDEX "idx_qrtz_ft_inst_job_req_rcvry" ON "public"."qrtz_fired_triggers" USING btree (sched_name, instance_name, requests_recovery);
CREATE INDEX "idx_qrtz_ft_j_g" ON "public"."qrtz_fired_triggers" USING btree (sched_name, job_name, job_group);
CREATE INDEX "idx_qrtz_ft_jg" ON "public"."qrtz_fired_triggers" USING btree (sched_name, job_group);
CREATE INDEX "idx_qrtz_ft_t_g" ON "public"."qrtz_fired_triggers" USING btree (sched_name, trigger_name, trigger_group);
CREATE INDEX "idx_qrtz_ft_tg" ON "public"."qrtz_fired_triggers" USING btree (sched_name, trigger_group);
CREATE INDEX "idx_qrtz_ft_trig_inst_name" ON "public"."qrtz_fired_triggers" USING btree (sched_name, instance_name);

-- ----------------------------
-- Primary Key structure for table qrtz_fired_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_fired_triggers" ADD PRIMARY KEY ("sched_name", "entry_id");

-- ----------------------------
-- Indexes structure for table qrtz_job_details
-- ----------------------------
CREATE INDEX "idx_qrtz_j_grp" ON "public"."qrtz_job_details" USING btree (sched_name, job_group);
CREATE INDEX "idx_qrtz_j_req_recovery" ON "public"."qrtz_job_details" USING btree (sched_name, requests_recovery);

-- ----------------------------
-- Primary Key structure for table qrtz_job_details
-- ----------------------------
ALTER TABLE "public"."qrtz_job_details" ADD PRIMARY KEY ("sched_name", "job_name", "job_group");

-- ----------------------------
-- Primary Key structure for table qrtz_locks
-- ----------------------------
ALTER TABLE "public"."qrtz_locks" ADD PRIMARY KEY ("sched_name", "lock_name");

-- ----------------------------
-- Primary Key structure for table qrtz_paused_trigger_grps
-- ----------------------------
ALTER TABLE "public"."qrtz_paused_trigger_grps" ADD PRIMARY KEY ("sched_name", "trigger_group");

-- ----------------------------
-- Primary Key structure for table qrtz_scheduler_state
-- ----------------------------
ALTER TABLE "public"."qrtz_scheduler_state" ADD PRIMARY KEY ("sched_name", "instance_name");

-- ----------------------------
-- Primary Key structure for table qrtz_simple_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_simple_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Primary Key structure for table qrtz_simprop_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_simprop_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Indexes structure for table qrtz_triggers
-- ----------------------------
CREATE INDEX "idx_qrtz_t_c" ON "public"."qrtz_triggers" USING btree (sched_name, calendar_name);
CREATE INDEX "idx_qrtz_t_g" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_group);
CREATE INDEX "idx_qrtz_t_j" ON "public"."qrtz_triggers" USING btree (sched_name, job_name, job_group);
CREATE INDEX "idx_qrtz_t_jg" ON "public"."qrtz_triggers" USING btree (sched_name, job_group);
CREATE INDEX "idx_qrtz_t_n_g_state" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_group, trigger_state);
CREATE INDEX "idx_qrtz_t_n_state" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_name, trigger_group, trigger_state);
CREATE INDEX "idx_qrtz_t_next_fire_time" ON "public"."qrtz_triggers" USING btree (sched_name, next_fire_time);
CREATE INDEX "idx_qrtz_t_nft_misfire" ON "public"."qrtz_triggers" USING btree (sched_name, misfire_instr, next_fire_time);
CREATE INDEX "idx_qrtz_t_nft_st" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_state, next_fire_time);
CREATE INDEX "idx_qrtz_t_nft_st_misfire" ON "public"."qrtz_triggers" USING btree (sched_name, misfire_instr, next_fire_time, trigger_state);
CREATE INDEX "idx_qrtz_t_nft_st_misfire_grp" ON "public"."qrtz_triggers" USING btree (sched_name, misfire_instr, next_fire_time, trigger_group, trigger_state);
CREATE INDEX "idx_qrtz_t_state" ON "public"."qrtz_triggers" USING btree (sched_name, trigger_state);

-- ----------------------------
-- Primary Key structure for table qrtz_triggers
-- ----------------------------
ALTER TABLE "public"."qrtz_triggers" ADD PRIMARY KEY ("sched_name", "trigger_name", "trigger_group");

-- ----------------------------
-- Primary Key structure for table sms_message
-- ----------------------------
ALTER TABLE "public"."sms_message" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table supplier
-- ----------------------------
ALTER TABLE "public"."supplier" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table t_trade
-- ----------------------------
ALTER TABLE "public"."t_trade" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_blob_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_blob_triggers" ADD FOREIGN KEY ("sched_name", "trigger_name", "trigger_group") REFERENCES "public"."qrtz_triggers" ("sched_name", "trigger_name", "trigger_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_cron_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_cron_triggers" ADD FOREIGN KEY ("sched_name", "trigger_name", "trigger_group") REFERENCES "public"."qrtz_triggers" ("sched_name", "trigger_name", "trigger_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_simple_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_simple_triggers" ADD FOREIGN KEY ("sched_name", "trigger_name", "trigger_group") REFERENCES "public"."qrtz_triggers" ("sched_name", "trigger_name", "trigger_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_simprop_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_simprop_triggers" ADD FOREIGN KEY ("sched_name", "trigger_name", "trigger_group") REFERENCES "public"."qrtz_triggers" ("sched_name", "trigger_name", "trigger_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."qrtz_triggers"
-- ----------------------------
ALTER TABLE "public"."qrtz_triggers" ADD FOREIGN KEY ("sched_name", "job_name", "job_group") REFERENCES "public"."qrtz_job_details" ("sched_name", "job_name", "job_group") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Records of c_bank
-- ----------------------------
INSERT INTO "public"."c_bank" VALUES ('1', 'BOC', '中国银行', '2014-09-27 10:30:22', 'BankofChina', 'Y', '2014-11-05 16:15:10.359');
INSERT INTO "public"."c_bank" VALUES ('2', 'ICBC', '中国工商银行', '2014-09-27 10:33:35', 'Industrial and Commercial Bank of China', 'Y', '2014-09-27 10:33:35');
INSERT INTO "public"."c_bank" VALUES ('3', 'ABC', '中国农业银行', '2014-09-27 10:35:24', 'Agricultural Bank of China', 'Y', '2014-09-27 10:35:24');
INSERT INTO "public"."c_bank" VALUES ('4', 'CCB', '中国建设银行', '2014-09-27 10:38:15', 'China Construction Bank', 'Y', '2014-09-27 10:38:15');
INSERT INTO "public"."c_bank" VALUES ('5', 'BCM', '交通银行', '2014-09-27 10:44:28', 'BANK OF COMMUNICATIONS ', 'Y', '2014-09-27 10:44:28');
INSERT INTO "public"."c_bank" VALUES ('6', 'PSBC', '中国邮政储蓄银行', '2014-09-27 10:46:43', 'POSTAL SAVINGS BANK OF CHINA', 'Y', '2014-09-27 10:46:43');
INSERT INTO "public"."c_bank" VALUES ('7', 'CITIC', '中信银行', '2014-09-27 10:52:21', 'CHINA CITIC BANK', 'Y', '2014-09-27 10:52:21');
INSERT INTO "public"."c_bank" VALUES ('8', 'SPDB', '上海浦东发展银行', '2014-09-27 10:54:56', 'Shanghai Pudong Development Bank', 'Y', '2014-09-27 10:54:56');
INSERT INTO "public"."c_bank" VALUES ('9', 'CIB', '兴业银行', '2014-09-27 10:56:31', 'Industrial Bank', 'Y', '2014-09-27 10:56:31');
INSERT INTO "public"."c_bank" VALUES ('10', 'CMBC', '中国民生银行', '2014-09-27 11:01:38', 'China Minsheng Banking Corp', 'Y', '2014-09-27 11:01:38');
INSERT INTO "public"."c_bank" VALUES ('11', 'CEB', '中国光大银行', '2014-09-27 11:06:53', 'CHINA EVERBRIGHT BANK', 'Y', '2014-09-27 11:06:53');
INSERT INTO "public"."c_bank" VALUES ('12', 'PAB', '平安银行', '2014-09-27 11:09:09', 'Ping An Bank ', 'Y', '2014-09-27 11:09:09');
INSERT INTO "public"."c_bank" VALUES ('13', 'HXB', '华夏银行', '2014-09-27 11:10:44', 'Hua Xia Bank', 'Y', '2014-09-27 11:10:44');
INSERT INTO "public"."c_bank" VALUES ('14', 'BOB', '北京银行', '2014-09-27 11:13:10', 'Bank of Beijing ', 'Y', '2014-09-27 11:13:10');
INSERT INTO "public"."c_bank" VALUES ('15', 'CGB', '广东发展银行', '2014-09-27 11:15:13', 'China Guangfa Bank', 'Y', '2014-09-27 11:15:13');
INSERT INTO "public"."c_bank" VALUES ('16', 'BOS', '上海银行', '2014-09-27 11:17:00', 'Bank of Shanghai', 'Y', '2014-09-27 11:17:00');
INSERT INTO "public"."c_bank" VALUES ('17', 'EGB', '恒丰银行', '2014-09-27 11:23:26', 'Evergrowing Bank', 'Y', '2014-09-27 11:23:26');
INSERT INTO "public"."c_bank" VALUES ('18', 'BRCB', '北京农村商业银行', '2014-09-27 11:30:24', 'BEIJING RURAL COMMERCIAL BANK', 'Y', '2014-09-27 11:30:24');
INSERT INTO "public"."c_bank" VALUES ('19', 'CRCB', '重庆农村商业银行', '2014-09-27 11:32:53', 'CHONGQING RURAL COMMERCLAL BANK', 'Y', '2014-09-27 11:32:53');
INSERT INTO "public"."c_bank" VALUES ('20', 'CBB', '渤海银行', '2014-09-27 11:36:08', 'China Bohai Bank', 'Y', '2014-09-27 11:36:08');
INSERT INTO "public"."c_bank" VALUES ('21', 'SRCB', '上海农村商业银行', '2014-09-27 11:40:34', 'Shanghai Rural Commercial Bank', 'Y', '2014-09-27 11:40:34');
INSERT INTO "public"."c_bank" VALUES ('22', 'BON', '南京银行', '2014-09-27 11:47:31', 'BANK OF NANJING', 'Y', '2014-09-27 11:49:07');
INSERT INTO "public"."c_bank" VALUES ('23', 'BOJ', '江苏银行', '2014-09-27 11:21:09', 'BANK OF JIANGSU', 'Y', '2014-09-27 11:49:25');
INSERT INTO "public"."c_bank" VALUES ('24', 'CMB', '招商银行', '2014-09-27 10:48:48', 'China Merchants Bank', 'Y', '2014-09-27 12:04:58');

-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO "public"."parameter" VALUES ('1', null, '2014-09-22 11:47:15', 'N', null, '2014-09-22 11:47:15', '实名认证-URL地址', 'CERTIFY_URL', 'http://service.sfxxrz.com/IdentifierService.svc');
INSERT INTO "public"."parameter" VALUES ('2', null, '2014-09-22 11:46:50', 'N', null, '2014-09-22 11:46:50', '实名认证-帐号密码', 'CERTIFY_PASSWORD', '0DEi9dPb');
INSERT INTO "public"."parameter" VALUES ('3', null, '2014-09-23 12:13:14', 'N', null, '2014-09-23 12:13:14', '缓存存在时间(min)', 'CACHE_EXPIRY', '60');
INSERT INTO "public"."parameter" VALUES ('4', null, '2014-09-15 18:28:41', 'N', null, '2014-09-15 18:28:41', '登录失败没到最大次数，隔XXX时间后失败次数清0的时间(min)', 'LOGIN_PERIOD', '60');
INSERT INTO "public"."parameter" VALUES ('5', null, '2014-09-15 18:27:11', 'N', null, '2014-09-15 18:27:11', '验证码在单位时间内最大次数', 'VERIFYCODE_MAX', '5');
INSERT INTO "public"."parameter" VALUES ('6', null, '2014-09-15 18:28:27', 'N', null, '2014-09-15 18:28:27', '密码允许错误最大次数', 'PWD_MAX', '5');
INSERT INTO "public"."parameter" VALUES ('7', null, '2014-09-15 18:28:13', 'N', null, '2014-09-15 18:28:13', '验证码时效时间(min)', 'VERIFYCODE_EXPIRY', '60');
INSERT INTO "public"."parameter" VALUES ('8', null, '2014-09-22 11:46:26', 'N', null, '2014-09-22 11:46:26', '实名认证-帐号用户名', 'CERTIFY_USERNAME', 'yyzc_admin');
INSERT INTO "public"."parameter" VALUES ('9', null, '2014-09-15 18:28:51', 'N', null, '2014-09-15 18:28:51', '用户解锁时间(min)', 'USERUNLOCK_PERIOD', '60');
INSERT INTO "public"."parameter" VALUES ('10', null, '2014-09-15 18:27:58', 'N', null, '2014-09-15 18:27:58', '验证码最大次数的单位时间(min)', 'VERIFYCODE_TIMES', '60');
INSERT INTO "public"."parameter" VALUES ('11', null, '2014-09-25 11:36:18', 'N', null, '2014-09-25 11:36:18', '实名认证-N真实调用/非N测试模式', 'CERTIFY_TEST', 'Y');
INSERT INTO "public"."parameter" VALUES ('12', null, '2014-09-18 10:26:36', 'N', null, '2014-09-18 10:26:36', '暂时锁定的时间(min)', 'RELIEVE_SUSLOCK_PERIOD', '30');
INSERT INTO "public"."parameter" VALUES ('13', null, '2014-09-23 12:13:33', 'N', null, '2014-09-23 12:13:33', 'cookie存在时间(min)', 'COOKIE_EXPIRY', '1440');
INSERT INTO "public"."parameter" VALUES ('14', null, '2014-09-23 12:13:48', 'N', null, '2014-09-23 12:13:48', '后台CustomerSession有效时间(min)，一般与COOKIE_EXPIRY存在时间一致', 'SESSION_EXPIRY', '1440');
INSERT INTO "public"."parameter" VALUES ('15', null, '2014-10-15 19:48:37', 'N', null, '2014-10-15 19:48:37', '短信接口-URL地址', 'SMS_URL', 'http://sms.2office.net:8080/WebService/sms3.aspx');
INSERT INTO "public"."parameter" VALUES ('16', null, '2014-10-15 19:48:58', 'N', null, '2014-10-15 19:48:58', '短信接口-第二办公室门牌号码', 'SMS_ACCOUNT', '2523040');
INSERT INTO "public"."parameter" VALUES ('17', null, '2014-10-15 19:49:20', 'N', null, '2014-10-15 19:49:20', '短信接口-密码', 'SMS_PASSWORD', 'yiyuezc597');
INSERT INTO "public"."parameter" VALUES ('18', null, '2014-10-15 19:49:41', 'N', null, '2014-10-15 19:49:41', '短信接口-通道编号', 'SMS_CHANNEL', '252304001');
INSERT INTO "public"."parameter" VALUES ('19', null, '2014-10-15 19:50:00', 'N', null, '2014-10-15 19:50:00', '短信接口-授权码', 'SMS_WARRANTYCODE', '9a15294089130ec6a8d27502d808a2a1');
INSERT INTO "public"."parameter" VALUES ('20', null, '2014-10-15 19:53:54', 'N', null, '2014-10-15 19:53:54', '短信接口-N真实调用/非N 测试模式', 'SMS_TEST', 'N');
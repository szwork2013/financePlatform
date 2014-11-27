/*
Navicat PGSQL Data Transfer

Source Server         : mars
Source Server Version : 90305
Source Host           : 192.168.1.95:5432
Source Database       : sunlightsdev
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90305
File Encoding         : 65001

Date: 2014-11-12 16:07:45
*/


-- ----------------------------
-- Sequence structure for cust_seq
-- ----------------------------
DROP SEQUENCE "public"."cust_seq";
CREATE SEQUENCE "public"."cust_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 9999999999
START 22
CACHE 1
CYCLE;
COMMENT ON SEQUENCE "public"."cust_seq" IS
'客户号序列';
SELECT
  setval('"public"."cust_seq"', 22, TRUE);

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
SELECT
  setval('"public"."hibernate_sequence"', 14, TRUE);

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
START 392
CACHE 1;
SELECT
  setval('"public"."src"', 392, TRUE);

-- ----------------------------
-- Sequence structure for trade_seq
-- ----------------------------
DROP SEQUENCE "public"."trade_seq";
CREATE SEQUENCE "public"."trade_seq"
INCREMENT 1
MINVALUE 1
MAXVALUE 9999
START 19
CACHE 1
CYCLE;
COMMENT ON SEQUENCE "public"."trade_seq" IS
'交易流水号';
SELECT
  setval('"public"."trade_seq"', 19, TRUE);

-- ----------------------------
-- Table structure for c_bank
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_bank";
CREATE TABLE "public"."c_bank" (
  "id"          INT8 NOT NULL,
  "bank_code"   VARCHAR(40) COLLATE "default",
  "bank_name"   VARCHAR(50) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "en_name"     VARCHAR(50) COLLATE "default",
  "status"      VARCHAR(1) COLLATE "default",
  "update_time" TIMESTAMP(6)
)
WITH (OIDS = FALSE);
COMMENT ON COLUMN "public"."c_bank"."bank_code" IS '银行编码';
COMMENT ON COLUMN "public"."c_bank"."bank_name" IS '银行名称';
COMMENT ON COLUMN "public"."c_bank"."en_name" IS '英文名';
COMMENT ON COLUMN "public"."c_bank"."status" IS '状态';

-- ----------------------------
-- Table structure for c_bank_card
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_bank_card";
CREATE TABLE "public"."c_bank_card" (
  "id"           INT8 NOT NULL,
  "bank_card_no" VARCHAR(40) COLLATE "default",
  "bank_code"    VARCHAR(40) COLLATE "default",
  "status"       VARCHAR(1) COLLATE "default",
  "bank_type"    VARCHAR(1) COLLATE "default",
  "create_time"  TIMESTAMP(6),
  "customer_id"  VARCHAR(30) COLLATE "default",
  "bank_serial"  VARCHAR(100) COLLATE "default",
  "update_time"  TIMESTAMP(6),
  "bank_id"      INT8
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for c_customer
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_customer";
CREATE TABLE "public"."c_customer" (
  "id"              INT8 NOT NULL,
  "customer_id"     VARCHAR(30) COLLATE "default",
  "customer_type"   VARCHAR(1) COLLATE "default",
  "device_no"       VARCHAR(50) COLLATE "default",
  "email"           VARCHAR(50) COLLATE "default",
  "identity_number" VARCHAR(30) COLLATE "default",
  "identity_typer"  VARCHAR(1) COLLATE "default",
  "login_id"        VARCHAR(20) COLLATE "default",
  "login_password"  VARCHAR(40) COLLATE "default",
  "mobile"          VARCHAR(11) COLLATE "default",
  "nick_name"       VARCHAR(50) COLLATE "default",
  "pic_way"         VARCHAR(40) COLLATE "default",
  "property"        VARCHAR(1) COLLATE "default",
  "qq"              VARCHAR(20) COLLATE "default",
  "real_name"       VARCHAR(20) COLLATE "default",
  "referral_code"   VARCHAR(10) COLLATE "default",
  "reg_channel"     VARCHAR(1) COLLATE "default",
  "reg_way"         VARCHAR(1) COLLATE "default",
  "status"          VARCHAR(1) COLLATE "default",
  "weibo"           VARCHAR(30) COLLATE "default",
  "weixin"          VARCHAR(30) COLLATE "default",
  "create_by"       VARCHAR(30) COLLATE "default",
  "create_time"     TIMESTAMP(6),
  "update_by"       VARCHAR(30) COLLATE "default",
  "update_time"     TIMESTAMP(6)
)
WITH (OIDS = FALSE);
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
COMMENT ON COLUMN "public"."c_customer"."create_by" IS '创建人';
COMMENT ON COLUMN "public"."c_customer"."create_time" IS '创建时间';

-- ----------------------------
-- Table structure for c_customer_gesture
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_customer_gesture";
CREATE TABLE "public"."c_customer_gesture" (
  "id"               INT8 NOT NULL,
  "device_no"        VARCHAR(40) COLLATE "default",
  "gesture_password" VARCHAR(40) COLLATE "default",
  "status"           VARCHAR(1) COLLATE "default",
  "customer_id"      VARCHAR(30) COLLATE "default",
  "update_time"      TIMESTAMP(6),
  "create_time"      TIMESTAMP(6)
)
WITH (OIDS = FALSE);
COMMENT ON COLUMN "public"."c_customer_gesture"."device_no" IS '设备号';
COMMENT ON COLUMN "public"."c_customer_gesture"."gesture_password" IS '手势密码';
COMMENT ON COLUMN "public"."c_customer_gesture"."status" IS 'Y失效 N有效';
COMMENT ON COLUMN "public"."c_customer_gesture"."customer_id" IS '客户号';
COMMENT ON COLUMN "public"."c_customer_gesture"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."c_customer_gesture"."create_time" IS '创建时间';

-- ----------------------------
-- Table structure for c_customer_session
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_customer_session";
CREATE TABLE "public"."c_customer_session" (
  "id"             INT8 NOT NULL,
  "client_address" VARCHAR(40) COLLATE "default",
  "device_name"    VARCHAR(40) COLLATE "default",
  "device_no"      VARCHAR(40) COLLATE "default",
  "status"         VARCHAR(1) COLLATE "default",
  "token"          VARCHAR(400) COLLATE "default",
  "customer_id"    VARCHAR(30) COLLATE "default",
  "create_time"    TIMESTAMP(6),
  "update_time"    TIMESTAMP(6)
)
WITH (OIDS = FALSE);
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
  "id"          INT8 NOT NULL,
  "device_no"   VARCHAR(40) COLLATE "default",
  "mobile"      VARCHAR(11) COLLATE "default",
  "status"      VARCHAR(1) COLLATE "default",
  "verify_code" VARCHAR(40) COLLATE "default",
  "verify_type" VARCHAR(20) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "update_time" TIMESTAMP(6)
)
WITH (OIDS = FALSE);
COMMENT ON COLUMN "public"."c_customer_verify_code"."device_no" IS '设备号';
COMMENT ON COLUMN "public"."c_customer_verify_code"."mobile" IS '手机号';
COMMENT ON COLUMN "public"."c_customer_verify_code"."status" IS 'Y失效 N有效';
COMMENT ON COLUMN "public"."c_customer_verify_code"."verify_code" IS '验证码';
COMMENT ON COLUMN "public"."c_customer_verify_code"."verify_type" IS '类型';

-- ----------------------------
-- Table structure for c_feedback
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_feedback";
CREATE TABLE "public"."c_feedback" (
  "id"          INT8 NOT NULL,
  "customer_id" VARCHAR(30) COLLATE "default",
  "context"     TEXT COLLATE "default",
  "mobile"      VARCHAR(20) COLLATE "default",
  "remark"      TEXT COLLATE "default",
  "status"      VARCHAR(50) COLLATE "default",
  "device_no"   VARCHAR(50) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "update_time" TIMESTAMP(6),
  "update_by"   VARCHAR(30) COLLATE "default"
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for c_fund_open_account
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_fund_open_account";
CREATE TABLE "public"."c_fund_open_account" (
  "id"               INT8,
  "bank_card_no"     VARCHAR(32) COLLATE "default",
  "bank_code"        VARCHAR(20) COLLATE "default",
  "bank_buyer_name"  VARCHAR(10) COLLATE "default",
  "branch_bank_name" VARCHAR(60) COLLATE "default",
  "customer_id"      VARCHAR(30) COLLATE "default",
  "create_time"      TIMESTAMP(6),
  "update_time"      TIMESTAMP(6)
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for c_login_history
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_login_history";
CREATE TABLE "public"."c_login_history" (
  "id"          INT8 NOT NULL,
  "device_no"   VARCHAR(40) COLLATE "default",
  "gesture_ind" VARCHAR(1) COLLATE "default",
  "log_num"     INT8,
  "pwd_ind"     VARCHAR(1) COLLATE "default",
  "social_ind"  VARCHAR(1) COLLATE "default",
  "success_ind" VARCHAR(1) COLLATE "default",
  "customer_id" VARCHAR(30) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "login_time"  TIMESTAMP(6),
  "logout_time" TIMESTAMP(6),
  "update_time" TIMESTAMP(6)
)
WITH (OIDS = FALSE);
COMMENT ON COLUMN "public"."c_login_history"."device_no" IS '设备号';
COMMENT ON COLUMN "public"."c_login_history"."gesture_ind" IS 'Y手势登录 ';
COMMENT ON COLUMN "public"."c_login_history"."log_num" IS '登录失败次数';
COMMENT ON COLUMN "public"."c_login_history"."pwd_ind" IS 'Y密码登录';
COMMENT ON COLUMN "public"."c_login_history"."success_ind" IS '成功登录标志';
COMMENT ON COLUMN "public"."c_login_history"."customer_id" IS '客户号';
COMMENT ON COLUMN "public"."c_login_history"."login_time" IS '登录时间';
COMMENT ON COLUMN "public"."c_login_history"."logout_time" IS '登出时间';

-- ----------------------------
-- Table structure for c_open_account_pact
-- ----------------------------
DROP TABLE IF EXISTS "public"."c_open_account_pact";
CREATE TABLE "public"."c_open_account_pact" (
  "agreement_no"          VARCHAR(20) COLLATE "default" NOT NULL,
  "sign_channel"          VARCHAR(10) COLLATE "default",
  "agreement_start_date"  TIMESTAMP(6),
  "agreement_end_date"    TIMESTAMP(6),
  "agreement_effect_date" TIMESTAMP(6),
  "agreement_expiry_date" TIMESTAMP(6),
  "agreement_status"      VARCHAR(1) COLLATE "default",
  "sale_property"         VARCHAR(1) COLLATE "default",
  "user_attribution"      VARCHAR(1) COLLATE "default",
  "agreement_name"        VARCHAR(40) COLLATE "default",
  "agreement_file_type"   VARCHAR(10) COLLATE "default",
  "file_name"             VARCHAR(100) COLLATE "default",
  "file_path"             VARCHAR(200) COLLATE "default",
  "status"                VARCHAR(1) COLLATE "default",
  "create_date"           TIMESTAMP(6),
  "update_date"           TIMESTAMP(6)
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS "public"."dict";
CREATE TABLE "public"."dict" (
  "id"          INT8 NOT NULL,
  "code_cat"    VARCHAR(50) COLLATE "default",
  "code_key"    VARCHAR(50) COLLATE "default",
  "code_val"    VARCHAR(100) COLLATE "default",
  "seq_no"      INT4,
  "create_by"   VARCHAR(255) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "status"      VARCHAR(1) COLLATE "default",
  "sys_ind"     VARCHAR(1) COLLATE "default",
  "magic"       VARCHAR(255) COLLATE "default",
  "remarks"     VARCHAR(255) COLLATE "default"
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for f_acct_chang_flow
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_acct_chang_flow";
CREATE TABLE "public"."f_acct_chang_flow" (
  "id"           INT8 NOT NULL,
  "product_code" VARCHAR(8) COLLATE "default",
  "trade_no"     VARCHAR(20) COLLATE "default",
  "subject_no"   VARCHAR(6) COLLATE "default",
  "amount"       NUMERIC(18, 4),
  "create_time"  TIMESTAMP(6),
  "customer_id"  VARCHAR(30) COLLATE "default"
)
WITH (OIDS = FALSE);
COMMENT ON TABLE "public"."f_acct_chang_flow" IS '账户变动流水表';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."id" IS '账户变动流水ID';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."product_code" IS '产品编码';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."trade_no" IS '交易流水号';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."subject_no" IS '科目号';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."amount" IS '变动金额';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."f_acct_chang_flow"."customer_id" IS '客户号';

-- ----------------------------
-- Table structure for f_basic_account
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_basic_account";
CREATE TABLE "public"."f_basic_account" (
  "id"             INT8 NOT NULL,
  "account_no"     VARCHAR(30) COLLATE "default",
  "status"         CHAR(1) COLLATE "default",
  "balance"        NUMERIC(18, 4),
  "create_time"    TIMESTAMP(6),
  "update_time"    TIMESTAMP(6),
  "delete_time"    TIMESTAMP(6),
  "trade_password" VARCHAR(50) COLLATE "default",
  "cust_id"        VARCHAR(30) COLLATE "default"
)
WITH (OIDS = FALSE);
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
-- Table structure for f_fund_agreement
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_fund_agreement";
CREATE TABLE "public"."f_fund_agreement" (
  "id"           INT8                          NOT NULL,
  "customer_id"  VARCHAR(30) COLLATE "default",
  "fund_company_id" VARCHAR(100) COLLATE "default" NOT NULL,
  "create_time"  TIMESTAMP(6),
  "update_time"  TIMESTAMP(6)
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for f_holdcapital
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_holdcapital";
CREATE TABLE "public"."f_holdcapital" (
  "id"                INT8 NOT NULL,
  "create_time"       TIMESTAMP(6),
  "cust_id"           VARCHAR(255) COLLATE "default",
  "delete_time"       TIMESTAMP(6),
  "hold_capital"      NUMERIC(18, 4),
  "hold_capital_type" VARCHAR(50) COLLATE "default",
  "product_code"      VARCHAR(8) COLLATE "default",
  "product_name"      VARCHAR(100) COLLATE "default",
  "settle_date"       TIMESTAMP(6),
  "status"            VARCHAR(1) COLLATE "default",
  "total_profit"      NUMERIC(18, 4),
  "trade_amount"      NUMERIC(18, 4),
  "update_time"       TIMESTAMP(6),
  "yesterday_profit"  NUMERIC(18, 4),
  "product_type"      VARCHAR(50) COLLATE "default"
)
WITH (OIDS = FALSE);
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
  "id"               INT8 NOT NULL,
  "sub_account"      VARCHAR(10) COLLATE "default",
  "status"           CHAR(1) COLLATE "default",
  "balance"          NUMERIC(18, 4),
  "profit"           NUMERIC(18, 4),
  "yesterday_profit" NUMERIC(18, 4),
  "create_time"      TIMESTAMP(6),
  "update_time"      TIMESTAMP(6),
  "delete_time"      TIMESTAMP(6),
  "cust_id"          VARCHAR(30) COLLATE "default",
  "basic_account"    VARCHAR(30) COLLATE "default"
)
WITH (OIDS = FALSE);
COMMENT ON TABLE "public"."f_sub_account" IS '子账户';
COMMENT ON COLUMN "public"."f_sub_account"."id" IS '子账户ID';
COMMENT ON COLUMN "public"."f_sub_account"."sub_account" IS '子账户号';
COMMENT ON COLUMN "public"."f_sub_account"."status" IS '子账户状态';
COMMENT ON COLUMN "public"."f_sub_account"."balance" IS '余额';
COMMENT ON COLUMN "public"."f_sub_account"."profit" IS '累计收益';
COMMENT ON COLUMN "public"."f_sub_account"."yesterday_profit" IS '昨日收益';
COMMENT ON COLUMN "public"."f_sub_account"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."f_sub_account"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."f_sub_account"."delete_time" IS '删除时间';
COMMENT ON COLUMN "public"."f_sub_account"."cust_id" IS '客户号';
COMMENT ON COLUMN "public"."f_sub_account"."basic_account" IS '基本账户号';

-- ----------------------------
-- Table structure for f_subject
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_subject";
CREATE TABLE "public"."f_subject" (
  "id"          INT8 NOT NULL,
  "subject_no"  VARCHAR(6) COLLATE "default",
  "description" VARCHAR(20) COLLATE "default",
  "dc_flag"     VARCHAR(50) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "update_time" TIMESTAMP(6),
  "delete_time" TIMESTAMP(6)
)
WITH (OIDS = FALSE);
COMMENT ON TABLE "public"."f_subject" IS '科目表';
COMMENT ON COLUMN "public"."f_subject"."id" IS '科目ID';
COMMENT ON COLUMN "public"."f_subject"."subject_no" IS '科目号';
COMMENT ON COLUMN "public"."f_subject"."description" IS '科目说明';
COMMENT ON COLUMN "public"."f_subject"."dc_flag" IS '借贷标志  增加资金记贷方 C  较少资金记借方 D';
COMMENT ON COLUMN "public"."f_subject"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."f_subject"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."f_subject"."delete_time" IS '删除时间';

-- ----------------------------
-- Table structure for m_logging_event
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_logging_event";
CREATE TABLE "public"."m_logging_event" (
  "log_event_id"              INT8 NOT NULL,
  "log_event_arg0"            VARCHAR(10) COLLATE "default",
  "log_event_arg1"            VARCHAR(10) COLLATE "default",
  "log_event_arg2"            VARCHAR(10) COLLATE "default",
  "log_event_arg3"            VARCHAR(10) COLLATE "default",
  "log_event_caller_class"    VARCHAR(10) COLLATE "default",
  "log_event_caller_filename" VARCHAR(10) COLLATE "default",
  "log_event_caller_line"     VARCHAR(10) COLLATE "default",
  "log_event_caller_method"   VARCHAR(10) COLLATE "default",
  "log_event_format_msg"      VARCHAR(10) COLLATE "default",
  "log_event_level"           VARCHAR(10) COLLATE "default",
  "log_event_logger_name"     VARCHAR(10) COLLATE "default",
  "log_event_ref_flag"        INT4,
  "log_event_thread_name"     VARCHAR(10) COLLATE "default",
  "log_event_timestmp"        INT8
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for m_logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_logging_event_exception";
CREATE TABLE "public"."m_logging_event_exception" (
  "log_event_id"         INT8 NOT NULL,
  "log_event_i"          INT4,
  "log_event_trace_line" VARCHAR(10) COLLATE "default"
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for m_logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS "public"."m_logging_event_property";
CREATE TABLE "public"."m_logging_event_property" (
  "log_event_id"     INT8 NOT NULL,
  "log_mapped_key"   VARCHAR(10) COLLATE "default",
  "log_mapped_value" VARCHAR(10) COLLATE "default"
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for p_fund
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_fund";
CREATE TABLE "public"."p_fund" (
  "id"                    INT8 NOT NULL,
  "min_apply_amount"      NUMERIC(18, 4),
  "lowest_redemption"     INT8,
  "one_year_profit"       NUMERIC(18, 4),
  "million_of_profit"     NUMERIC(18, 4),
  "one_week_profit"       NUMERIC(18, 4),
  "nav_date"              TIMESTAMP(6),
  "is_apply"              VARCHAR(50) COLLATE "default",
  "is_redemption"         VARCHAR(50) COLLATE "default",
  "product_status"        VARCHAR(50) COLLATE "default",
  "deleted"               VARCHAR(50) COLLATE "default",
  "create_time"           TIMESTAMP(6),
  "update_time"           TIMESTAMP(6),
  "update_by"             VARCHAR(10) COLLATE "default",
  "create_by"             VARCHAR(10) COLLATE "default",
  "company_name"          VARCHAR(10) COLLATE "default",
  "chi_name"              VARCHAR(100) COLLATE "default",
  "chi_name_abbr"         VARCHAR(50) COLLATE "default",
  "eng_name"              VARCHAR(100) COLLATE "default",
  "eng_name_abbr"         VARCHAR(50) COLLATE "default",
  "secu_abbr"             VARCHAR(20) COLLATE "default",
  "fund_scale"            NUMERIC,
  "fund_type"             VARCHAR(50) COLLATE "default",
  "invest_period"         VARCHAR(50) COLLATE "default",
  "charge"                NUMERIC,
  "to_account_type"       VARCHAR(50) COLLATE "default",
  "supplier_id"           INT8,
  "risk_level"            VARCHAR(50) COLLATE "default",
  "fund_code"             VARCHAR(10) COLLATE "default",
  "init_buyed_count"      INT8,
  "one_month_buyed_count" INT8
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for p_fund_history
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_fund_history";
CREATE TABLE "public"."p_fund_history" (
  "id"                    INT8 NOT NULL,
  "min_apply_amount"      NUMERIC(18, 4),
  "lowest_redemption"     INT8,
  "one_year_profit"       NUMERIC(18, 4),
  "million_of_profit"     NUMERIC(18, 4),
  "one_week_profit"       NUMERIC(18, 4),
  "nav_date"              TIMESTAMP(6),
  "is_apply"              VARCHAR(50) COLLATE "default",
  "is_redemption"         VARCHAR(50) COLLATE "default",
  "product_status"        INT4,
  "deleted"               BOOL,
  "create_time"           TIMESTAMP(6),
  "update_time"           TIMESTAMP(6),
  "update_by"             VARCHAR(10) COLLATE "default",
  "create_by"             VARCHAR(10) COLLATE "default",
  "company_name"          VARCHAR(10) COLLATE "default",
  "chi_name"              VARCHAR(100) COLLATE "default",
  "chi_name_abbr"         VARCHAR(50) COLLATE "default",
  "eng_name"              VARCHAR(100) COLLATE "default",
  "eng_name_abbr"         VARCHAR(50) COLLATE "default",
  "secu_abbr"             VARCHAR(20) COLLATE "default",
  "fund_scale"            NUMERIC,
  "fund_type"             VARCHAR(50) COLLATE "default",
  "invest_period"         VARCHAR(50) COLLATE "default",
  "charge"                NUMERIC,
  "to_account_type"       VARCHAR(50) COLLATE "default",
  "supplier_id"           INT8,
  "risk_level"            VARCHAR(50) COLLATE "default",
  "fund_code"             VARCHAR(10) COLLATE "default",
  "init_buyed_count"      INT8,
  "one_month_buyed_count" INT8,
  "created_by"            VARCHAR(30) COLLATE "default",
  "onr_month_buyed_count" INT8,
  "suppiler_id"           INT8,
  "updated_by"            VARCHAR(30) COLLATE "default",
  "updated_time"          TIMESTAMP(6)
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for p_prd_type
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_prd_type";
CREATE TABLE "public"."p_prd_type" (
  "id"          INT8 NOT NULL,
  "code"        VARCHAR(8) COLLATE "default",
  "name"        VARCHAR(60) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "update_time" TIMESTAMP(6),
  "delete_time" TIMESTAMP(6)
)
WITH (OIDS = FALSE);
COMMENT ON TABLE "public"."p_prd_type" IS '产品类型表';
COMMENT ON COLUMN "public"."p_prd_type"."id" IS '主键';
COMMENT ON COLUMN "public"."p_prd_type"."code" IS '产品类型编码';
COMMENT ON COLUMN "public"."p_prd_type"."name" IS '产品类型名称';
COMMENT ON COLUMN "public"."p_prd_type"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."p_prd_type"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."p_prd_type"."delete_time" IS '删除时间';

-- ----------------------------
-- Table structure for p_product_manage
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_product_manage";
CREATE TABLE "public"."p_product_manage" (
  "id"             INT8 NOT NULL,
  "product_name"   VARCHAR(10) COLLATE "default",
  "begin_time"     TIMESTAMP(6),
  "end_date"       TIMESTAMP(6),
  "create_time"    TIMESTAMP(6),
  "update_time"    TIMESTAMP(6),
  "product_desc"   VARCHAR(100) COLLATE "default",
  "product_status" CHAR(50) COLLATE "default",
  "url"            VARCHAR(300) COLLATE "default",
  "product_code"   VARCHAR(10) COLLATE "default",
  "product_type"   VARCHAR(50) COLLATE "default"
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for p_product_recommend
-- ----------------------------
DROP TABLE IF EXISTS "public"."p_product_recommend";
CREATE TABLE "public"."p_product_recommend" (
  "id"             INT8 NOT NULL,
  "product_id"     INT8,
  "product_name"   VARCHAR(100) COLLATE "default",
  "recommend_type" VARCHAR(8) COLLATE "default",
  "product_code"   VARCHAR(8) COLLATE "default",
  "begin_date"     TIMESTAMP(6),
  "end_date"       TIMESTAMP(6),
  "temp_stop_date" TIMESTAMP(6),
  "priority_level" VARCHAR(50) COLLATE "default",
  "recommend_flag" VARCHAR(50) COLLATE "default",
  "recommend_desc" VARCHAR(50) COLLATE "default",
  "url"            VARCHAR(500) COLLATE "default"
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS "public"."parameter";
CREATE TABLE "public"."parameter" (
  "id"          INT8 NOT NULL,
  "description" VARCHAR(255) COLLATE "default",
  "name"        VARCHAR(50) COLLATE "default",
  "value"       VARCHAR(50) COLLATE "default",
  "status"      VARCHAR(1) COLLATE "default"
)
WITH (OIDS = FALSE);
COMMENT ON COLUMN "public"."parameter"."description" IS '描述';
COMMENT ON COLUMN "public"."parameter"."name" IS '参数名';
COMMENT ON COLUMN "public"."parameter"."value" IS '参数值';
COMMENT ON COLUMN "public"."parameter"."status" IS 'Y有效 N失效';

-- ----------------------------
-- Table structure for prd_account_config
-- ----------------------------
DROP TABLE IF EXISTS "public"."prd_account_config";
CREATE TABLE "public"."prd_account_config" (
  "id"            INT8 NOT NULL,
  "prd_type_code" VARCHAR(50) COLLATE "default",
  "sub_account"   VARCHAR(10) COLLATE "default",
  "create_time"   TIMESTAMP(6),
  "update_time"   TIMESTAMP(6),
  "delete_time"   TIMESTAMP(6)
)
WITH (OIDS = FALSE);
COMMENT ON TABLE "public"."prd_account_config" IS '产品-账户关系配置表';
COMMENT ON COLUMN "public"."prd_account_config"."id" IS '主键Id';
COMMENT ON COLUMN "public"."prd_account_config"."prd_type_code" IS '产品类型编码';
COMMENT ON COLUMN "public"."prd_account_config"."sub_account" IS '子账户号';
COMMENT ON COLUMN "public"."prd_account_config"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."prd_account_config"."update_time" IS '修改时间';
COMMENT ON COLUMN "public"."prd_account_config"."delete_time" IS '删除时间';


-- ----------------------------
-- Table structure for sms_message
-- ----------------------------
DROP TABLE IF EXISTS "public"."sms_message";
CREATE TABLE "public"."sms_message" (
  "id"          INT8 NOT NULL,
  "content"     VARCHAR(200) COLLATE "default",
  "mobile"      VARCHAR(11) COLLATE "default",
  "rec_status"  VARCHAR(40) COLLATE "default",
  "return_msg"  VARCHAR(200) COLLATE "default",
  "smsid"       VARCHAR(40) COLLATE "default",
  "success_ind" CHAR(1) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "update_time" TIMESTAMP(6)
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for sms_message
-- ----------------------------
DROP TABLE IF EXISTS "public"."f_shumi_account";
CREATE TABLE "public"."f_shumi_account" (
  "id"          INT8 NOT NULL,
  "customer_id"     VARCHAR(30) COLLATE "default",
  "shumi_tokenkey"      VARCHAR(500) COLLATE "default",
  "shumi_tokensecret"  VARCHAR(500) COLLATE "default",
  "shumi_username"  VARCHAR(50) COLLATE "default",
  "shumi_realname"       VARCHAR(50) COLLATE "default",
  "shumi_idnumber" VARCHAR(100) COLLATE "default",
  "shumi_bankname" VARCHAR(100) COLLATE "default",
  "shumi_bankcardno" VARCHAR(100) COLLATE "default",
  "shumi_bankserial" VARCHAR(100) COLLATE "default",
  "shumi_phonenum" VARCHAR(20) COLLATE "default",
  "shumi_email" VARCHAR(50) COLLATE "default",
  "create_time" TIMESTAMP(6),
  "update_time" TIMESTAMP(6)
)
WITH (OIDS = FALSE);

-- ----------------------------
-- Table structure for t_trade
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_trade";
CREATE TABLE "public"."t_trade" (
  "id"             INT8 NOT NULL,
  "trade_no"       VARCHAR(100) COLLATE "default",
  "fee"            NUMERIC(18, 4),
  "type"           VARCHAR(50) COLLATE "default",
  "trade_status"   VARCHAR(50) COLLATE "default",
  "confirm_status" VARCHAR(50) COLLATE "default",
  "trade_time"     TIMESTAMP(6),
  "create_time"    TIMESTAMP(6),
  "update_time"    TIMESTAMP(6),
  "delete_time"    TIMESTAMP(6),
  "cust_id"        VARCHAR(30) COLLATE "default",
  "bank_card_no"   VARCHAR(40) COLLATE "default",
  "bank_name"      VARCHAR(50) COLLATE "default",
  "pay_status"     VARCHAR(50) COLLATE "default",
  "trade_amount"   NUMERIC(18, 4),
  "product_code"   VARCHAR(8) COLLATE "default",
  "product_name"   VARCHAR(100) COLLATE "default",
  "product_price"  NUMERIC(18, 4),
  "quantity"       INT4,
  "holdcapital_id" INT8
)
WITH (OIDS = FALSE);
COMMENT ON TABLE "public"."t_trade" IS '交易表';
COMMENT ON COLUMN "public"."t_trade"."id" IS '交易主键';
COMMENT ON COLUMN "public"."t_trade"."trade_no" IS '交易流水号';
COMMENT ON COLUMN "public"."t_trade"."fee" IS '手续费';
COMMENT ON COLUMN "public"."t_trade"."type" IS '1:申购 2:赎回 3:分红';
COMMENT ON COLUMN "public"."t_trade"."trade_status" IS '1：存/取钱中、2：存/取钱成功、3：存/取失败【失败原因】';
COMMENT ON COLUMN "public"."t_trade"."confirm_status" IS '0-不需发送；1-待确认；2-待确认；3-部分确认；4-确认完成；5-确认失败';
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


DROP TABLE IF EXISTS F_ACTIVITY;

/*==============================================================*/
/* Table: F_ACTIVITY                                            */
/*==============================================================*/
CREATE TABLE F_ACTIVITY
(
  ID          INT8         NOT NULL,
  STATUS      VARCHAR(2)   NOT NULL,
  TITLE       VARCHAR(700) NOT NULL,
  BEGIN_TIME  DATE         NULL,
  END_TIME    DATE         NULL,
  SCENE       VARCHAR(10)  NOT NULL,
  APP_ID      VARCHAR(16)  NULL,
  IMAGE       VARCHAR(50)  NULL,
  URL         VARCHAR(50)  NULL,
  CLICK_TIME  INT8         NULL,
  TYPE        VARCHAR(10)  NULL,
  STYLE       VARCHAR(10)  NULL,
  CLICK_EVENT VARCHAR(6)   NULL,
  CREATE_TIME TIMESTAMP    NULL,
  UPDATE_TIME TIMESTAMP    NULL,
  CREATE_BY   VARCHAR(30)  NULL,
  UPDATE_BY   VARCHAR(30)  NULL,
  CONSTRAINT PK_F_ACTIVITY PRIMARY KEY (ID)
);

COMMENT ON COLUMN F_ACTIVITY.ID IS
'主键';

COMMENT ON COLUMN F_ACTIVITY.STATUS IS
'N表示正常   F表示禁用';

COMMENT ON COLUMN F_ACTIVITY.TITLE IS
'活动标题';

COMMENT ON COLUMN F_ACTIVITY.BEGIN_TIME IS
'活动开始时间';

COMMENT ON COLUMN F_ACTIVITY.END_TIME IS
'活动结束时间';

COMMENT ON COLUMN F_ACTIVITY.SCENE IS
'应用场景';

COMMENT ON COLUMN F_ACTIVITY.APP_ID IS
'投放产品ID';

COMMENT ON COLUMN F_ACTIVITY.IMAGE IS
'活动图片';

COMMENT ON COLUMN F_ACTIVITY.URL IS
'html5名称';

COMMENT ON COLUMN F_ACTIVITY.CLICK_TIME IS
'点击次数';

COMMENT ON COLUMN F_ACTIVITY.TYPE IS
'活动类型';

COMMENT ON COLUMN F_ACTIVITY.STYLE IS
'活动形式';

COMMENT ON COLUMN F_ACTIVITY.CLICK_EVENT IS
'点击事件';

COMMENT ON COLUMN F_ACTIVITY.CREATE_TIME IS
'创建时间';

COMMENT ON COLUMN F_ACTIVITY.UPDATE_TIME IS
'修改时间';

COMMENT ON COLUMN F_ACTIVITY.CREATE_BY IS
'创建人';

COMMENT ON COLUMN F_ACTIVITY.UPDATE_BY IS
'修改人';


DROP TABLE IF EXISTS F_REWARD_TYPE;
/*==============================================================*/
/* Table: F_REWARD_TYPE                                         */
/*==============================================================*/
CREATE TABLE F_REWARD_TYPE
(
  ID          INT8        NOT NULL,
  CODE        VARCHAR(6)  NOT NULL,
  NAME        VARCHAR(16) NOT NULL,
  UNIT        INT8        NOT NULL,
  CREATE_TIME TIMESTAMP   NULL,
  UPDATE_TIME TIMESTAMP   NULL,
  CREATE_BY   VARCHAR(30) NULL,
  UPDATE_BY   VARCHAR(30) NULL,
  CONSTRAINT PK_F_REWARD_TYPE PRIMARY KEY (ID)
);

COMMENT ON COLUMN F_REWARD_TYPE.ID IS
'主键';

COMMENT ON COLUMN F_REWARD_TYPE.CODE IS
'奖励类型编码';

COMMENT ON COLUMN F_REWARD_TYPE.NAME IS
'奖励类型名称';

COMMENT ON COLUMN F_REWARD_TYPE.UNIT IS
'单位';

COMMENT ON COLUMN F_REWARD_TYPE.CREATE_TIME IS
'创建时间';

COMMENT ON COLUMN F_REWARD_TYPE.UPDATE_TIME IS
'更新时间';

COMMENT ON COLUMN F_REWARD_TYPE.CREATE_BY IS
'创建人';

COMMENT ON COLUMN F_REWARD_TYPE.UPDATE_BY IS
'修改人';


DROP TABLE IF EXISTS F_EXCHANGE_REWARD_RULE;

/*==============================================================*/
/* Table: F_EXCHANGE_REWARD_RULE                                */
/*==============================================================*/
CREATE TABLE F_EXCHANGE_REWARD_RULE
(
  ID           INT8           NOT NULL,
  REWARD_TYPE  VARCHAR(6)     NULL,
  STATUS       VARCHAR(2)     NULL,
  EXCHAGE_TYPE VARCHAR(6)     NULL,
  RATE         DECIMAL(18, 4) NOT NULL,
  LIMIT_TIME   INT4           NULL,
  NOTICE_TIME  INT4           NULL,
  DELAY_TIME   INT4           NULL,
  CREATE_TIME  TIMESTAMP      NULL,
  UPDATE_TIME  TIMESTAMP      NULL,
  CREATE_BY    VARCHAR(30)    NULL,
  UPDATE_BY    VARCHAR(30)    NULL,
  CONSTRAINT PK_F_EXCHANGE_REWARD_RULE PRIMARY KEY (ID)
);

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.ID IS
'主键';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.REWARD_TYPE IS
'奖励类型';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.STATUS IS
'状态 N表示正常  F表示禁止使用';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.EXCHAGE_TYPE IS
'兑换类型';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.RATE IS
'兑换率';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.LIMIT_TIME IS
'兑换期限  1-1个月；2-2个月；3-3个月 4-6个月 5-两周';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.NOTICE_TIME IS
'兑换到期提醒  1-提前1星期通知 2-提前3天通知';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.DELAY_TIME IS
'兑换期限延长 1-积分有效期延长1周 2-积分有效期延长1月，并分享活动';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.CREATE_TIME IS
'创建时间';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.UPDATE_TIME IS
'修改时间';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.CREATE_BY IS
'创建人';

COMMENT ON COLUMN F_EXCHANGE_REWARD_RULE.UPDATE_BY IS
'修改人';


DROP TABLE IF EXISTS F_GET_REWARD_RULE;

/*==============================================================*/
/* Table: F_GET_REWARD_RULE                                     */
/*==============================================================*/
CREATE TABLE F_GET_REWARD_RULE (
  ID               INT8           NOT NULL,
  activity_id      INT8           NULL,
  reward_type      VARCHAR(6)     NULL,
  status           VARCHAR(2)     NOT NULL,
  should_reward    INT8           NOT NULL,
  real_reward      INT8           NULL,
  back_reward      INT8           NULL,
  effect_time      INT4           NULL,
  valid_time       INT4           NULL,
  total_limit_amt  INT8           NULL,
  product_type     VARCHAR(30)    NULL,
  product_code     VARCHAR(16)    NULL,
  activity_channel INT4           NULL,
  trade_amt        NUMERIC(18, 2) NULL,
  back_funds       NUMERIC(18, 2) NULL,
  create_time      TIMESTAMP      NULL,
  update_time      TIMESTAMP      NULL,
  create_by        VARCHAR(30)    NULL,
  update_by        VARCHAR(30)    NULL,
  CONSTRAINT PK_F_GET_REWARD_RULE PRIMARY KEY (ID)
);

COMMENT ON COLUMN F_GET_REWARD_RULE.ID IS
'主键';

COMMENT ON COLUMN F_GET_REWARD_RULE.activity_id IS
'活动Id';

COMMENT ON COLUMN F_GET_REWARD_RULE.reward_type IS
'奖励类型';

COMMENT ON COLUMN F_GET_REWARD_RULE.status IS
'状态  N表示正常  F表示禁用';

COMMENT ON COLUMN F_GET_REWARD_RULE.should_reward IS
'应发奖励';

COMMENT ON COLUMN F_GET_REWARD_RULE.real_reward IS
'实发奖励';

COMMENT ON COLUMN F_GET_REWARD_RULE.back_reward IS
'退回奖励';

COMMENT ON COLUMN F_GET_REWARD_RULE.effect_time IS
'奖励到账实效 1-实时到账 2-1到2个工作日 3-2到3个工作日 4-月末';

COMMENT ON COLUMN F_GET_REWARD_RULE.valid_time IS
'奖励有效时长  1-1个月；2-2个月；3-3个月 4-6个月 5-两周';

COMMENT ON COLUMN F_GET_REWARD_RULE.total_limit_amt IS
'奖励累计限额';

COMMENT ON COLUMN F_GET_REWARD_RULE.product_type IS
'参与活动产品类型';

COMMENT ON COLUMN F_GET_REWARD_RULE.product_code IS
'参与活动产品代码';

COMMENT ON COLUMN F_GET_REWARD_RULE.activity_channel IS
'活动渠道   1-金豆荚APP';

COMMENT ON COLUMN F_GET_REWARD_RULE.trade_amt IS
'交易金额';

COMMENT ON COLUMN F_GET_REWARD_RULE.back_funds IS
'交易退款总金额';

COMMENT ON COLUMN F_GET_REWARD_RULE.create_time IS
'创建时间';

COMMENT ON COLUMN F_GET_REWARD_RULE.update_time IS
'修改时间';

COMMENT ON COLUMN F_GET_REWARD_RULE.create_by IS
'创建人';

COMMENT ON COLUMN F_GET_REWARD_RULE.update_by IS
'修改人';


DROP TABLE IF EXISTS F_REWARD_COUNT;
/*==============================================================*/
/* Table: F_REWARD_COUNT                                        */
/*==============================================================*/
CREATE TABLE F_REWARD_COUNT
(
  ID            INT8           NOT NULL,
  customer_Id   VARCHAR(30)    NULL,
  REWARD_TYPE   VARCHAR(6)     NULL,
  GET_AMOUNT    INT8           NULL,
  HOLD_REWARD   INT8           NULL,
  FROZEN_REWARD INT8           NULL,
  GET_MONEY     DECIMAL(18, 2) NULL,
  HOLD_MONEY    DECIMAL(18, 2) NULL,
  FROZEN_MONEY  DECIMAL(18, 2) NULL,
  CREATE_TIME   TIMESTAMP      NULL,
  UPDATE_TIME   TIMESTAMP      NULL,
  CONSTRAINT PK_F_REWARD_COUNT PRIMARY KEY (ID)
);

COMMENT ON COLUMN F_REWARD_COUNT.ID IS
'主键';

COMMENT ON COLUMN F_REWARD_COUNT.REWARD_TYPE IS
'奖励类型';

COMMENT ON COLUMN F_REWARD_COUNT.GET_AMOUNT IS
'获取奖励数量：获取各种奖励类型的奖励的总和';

COMMENT ON COLUMN F_REWARD_COUNT.HOLD_REWARD IS
'持有奖励数量：现持有各种奖励类型的奖励的总和';

COMMENT ON COLUMN F_REWARD_COUNT.FROZEN_REWARD IS
'冻结奖励数量';

COMMENT ON COLUMN F_REWARD_COUNT.GET_MONEY IS
'获取奖励折现';

COMMENT ON COLUMN F_REWARD_COUNT.HOLD_MONEY IS
'持有奖励折现';

COMMENT ON COLUMN F_REWARD_COUNT.FROZEN_MONEY IS
'冻结奖励折现';

COMMENT ON COLUMN F_REWARD_COUNT.CREATE_TIME IS
'创建时间';

COMMENT ON COLUMN F_REWARD_COUNT.UPDATE_TIME IS
'修改时间';


/*==============================================================*/
/* Table: F_REWARD_FLOW                                         */
/*==============================================================*/
DROP TABLE IF EXISTS F_REWARD_FLOW;
/*==============================================================*/
/* Table: F_REWARD_FLOW                                         */
/*==============================================================*/
CREATE TABLE F_REWARD_FLOW
(
  ID             INT8           NOT NULL,
  customer_Id    VARCHAR(30)    NULL,
  ACTIVITY_ID    INT8           NULL,
  Activity_title VARCHAR(700)   NULL,
  SCENE          VARCHAR(10)    NULL,
  STATUS         INT4           NULL,
  REWARD_TYPE    VARCHAR(6)     NULL,
  OPERATOR_TYPE  INT4           NULL,
  REWARD_AMT     INT8           NULL,
  MONEY          DECIMAL(18, 2) NULL,
  CREATE_TIME    TIMESTAMP      NULL,
  CONSTRAINT PK_F_REWARD_FLOW PRIMARY KEY (ID)
);

COMMENT ON COLUMN F_REWARD_FLOW.ID IS
'主键';

COMMENT ON COLUMN F_REWARD_FLOW.ACTIVITY_ID IS
'活动ID';

COMMENT ON COLUMN F_REWARD_FLOW.Activity_title IS
'活动标题';

COMMENT ON COLUMN F_REWARD_FLOW.SCENE IS
'应用场景';

COMMENT ON COLUMN F_REWARD_FLOW.STATUS IS
'状态 0-未发放，1-已发放，2-发放失败，4-撤回成功，5-撤回失败 6-已兑换';

COMMENT ON COLUMN F_REWARD_FLOW.REWARD_TYPE IS
'奖励类型';

COMMENT ON COLUMN F_REWARD_FLOW.OPERATOR_TYPE IS
'1表示获取  2表示兑换';

COMMENT ON COLUMN F_REWARD_FLOW.REWARD_AMT IS
'奖励数量';

COMMENT ON COLUMN F_REWARD_FLOW.MONEY IS
'奖励折现';

COMMENT ON COLUMN F_REWARD_FLOW.CREATE_TIME IS
'创建时间';


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
-- Primary Key structure for table c_feedback
-- ----------------------------
ALTER TABLE "public"."c_feedback" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_login_history
-- ----------------------------
ALTER TABLE "public"."c_login_history" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table c_open_account_pact
-- ----------------------------
ALTER TABLE "public"."c_open_account_pact" ADD PRIMARY KEY ("agreement_no");

-- ----------------------------
-- Primary Key structure for table dict
-- ----------------------------
ALTER TABLE "public"."dict" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table f_acct_chang_flow
-- ----------------------------
ALTER TABLE "public"."f_acct_chang_flow" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table f_basic_account
-- ----------------------------
ALTER TABLE "public"."f_basic_account" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table f_fund_agreement
-- ----------------------------
ALTER TABLE "public"."f_fund_agreement" ADD PRIMARY KEY ("id");

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


--2014-11-20
DROP TABLE IF EXISTS C_SUPPLIER;
CREATE TABLE C_SUPPLIER
(
  ID                    BIGINT         NOT NULL,
  SUPPLIER_CODE         VARCHAR(50)    NULL,
  SUPPLIER_TYPE         VARCHAR(50)    NULL,
  MERCHANT_NAME         VARCHAR(100)   NULL,
  DISPLAY_NAME          VARCHAR(50)    NULL,
  BELONG_ADDRESS        VARCHAR(200)   NULL,
  CONTACT_CALLNO        VARCHAR(15)    NULL,
  CORPORATE_PROPERTY    VARCHAR(20)    NULL,
  WEBSITE_ADDRESS       VARCHAR(50)    NULL,
  MERCHANT_INTRODUCTION VARCHAR(200)   NULL,
  REG_CAPITAL           DECIMAL(18, 4) NULL,
  PAID_UP_CAPITAL       DECIMAL(18, 4) NULL,
  REG_PROV              VARCHAR(10)    NULL,
  REG_CITY              VARCHAR(10)    NULL,
  REG_ADDRESS           VARCHAR(100)   NULL,
  CREATE_TIME           TIMESTAMP      NULL,
  UPDATE_TIME           TIMESTAMP      NULL,
  PRIMARY KEY (ID)
);

--Y
/*==============================================================*/
/* Table: P_fund_company                                        */
/*==============================================================*/
DROP TABLE IF EXISTS P_FUND_COMPANY;
CREATE TABLE
  P_FUND_COMPANY
(
  ID                 BIGINT NOT NULL,
  COMPANY_NAME       CHARACTER VARYING(50),
  FUND_COMPANY_ID    CHARACTER VARYING(100),
  PIN_YIN_CODE       CHARACTER VARYING(30),
  ABBR_NAME          CHARACTER VARYING(50),
  BACKGROUND         TEXT,
  CONTACT_ADDR       CHARACTER VARYING(500),
  DIRECT_SELL_URL    CHARACTER VARYING(500),
  EMAIL              CHARACTER VARYING(50),
  ESTABLISHMENT_DATE TIMESTAMP(6) WITHOUT TIME ZONE,
  FAX                CHARACTER VARYING(100),
  GENERAL_MANAGER    CHARACTER VARYING(20),
  LEGAL_REPR         CHARACTER VARYING(20),
  LINK_MAN           CHARACTER VARYING(20),
  OFFICE_ADDR        CHARACTER VARYING(500),
  REG_ADDR           CHARACTER VARYING(500),
  REG_CAPITAL        NUMERIC(16, 5),
  SERVICE_LINE       CHARACTER VARYING(20),
  TEL                CHARACTER VARYING(100),
  WEB_SITE           CHARACTER VARYING(200),
  ZIP_CODE           CHARACTER VARYING(10),
  IS_FUND_COMPANY    CHARACTER VARYING(50),
  CONSTRAINT PK_P_FUND_COMPANY PRIMARY KEY (ID)
);


--2014-11-19-Y
/*==============================================================*/
/* Table: FundNav                                               */
/*==============================================================*/
DROP TABLE IF EXISTS FUNDNAV;
CREATE TABLE FUNDNAV
(
  FUNDCODE                VARCHAR(30)    NOT NULL,
  ALIASCODE               VARCHAR(30)    NULL,
  FUNDNAME                TEXT           NULL,
  FUNDNAMEABBR            TEXT           NULL,
  CURR_DATE               TEXT           NULL,
  FUND_TYPE               INT            NULL,
  INVESTMENT_TYPE         INT            NULL,
  NETVALUE                DECIMAL(16, 5) NULL,
  TOTAL_NETVALUE          DECIMAL(16, 5) NULL,
  PERCENT                 DECIMAL(16, 5) NULL,
  INCOME_PER_TEN_THOUSAND DECIMAL(16, 5) NULL,
  PERCENT_SEVEN_DAYS      DECIMAL(16, 5) NULL,
  YIELD_1M                DECIMAL(16, 5) NULL,
  YIELD_3M                DECIMAL(16, 5) NULL,
  YIELD_6M                DECIMAL(16, 5) NULL,
  YIELD_12M               DECIMAL(16, 5) NULL,
  YIELD_THIS_YEAR         DECIMAL(16, 5) NULL,
  CGS_3_YEAR              DECIMAL(16, 5) NULL,
  LASTEST_TOTAL_ASSET     DECIMAL(16, 5) NULL,
  ON_SALE                 INT            NULL,
  RISK_LEVEL              VARCHAR(50)    NULL,
  SHARE_TYPE              VARCHAR(50)    NULL,
  PURCHASE_STATE          INT            NULL,
  SUBSCRIBE_STATE         INT            NULL,
  AIP_STATE               INT            NULL,
  RECOMMENDATION          INT            NULL,
  CHARGE_RATE_VALUE       DECIMAL(16, 5) NULL,
  DISCOUNT                DECIMAL(16, 5) NULL,
  SALE_CHARGE_RATE_VALUE  DECIMAL(16, 5) NULL,
  IS_MONETARY             INT            NULL,
  IS_STF                  INT            NULL,
  PURCHASE_LIMIT_MIN      DECIMAL(16, 5) NULL,
  REDEEM_LIMIT_MIN        DECIMAL(16, 5) NULL,
  RAPID_REDEEM            INT            NULL,
  IA_GUID                 VARCHAR(100)   NULL,
  FUND_MANAGEMENT_FEES    TEXT           NULL,
  FUND_TRUSTEE_FEES       TEXT           NULL,
  CREATE_TIME             TIMESTAMP      NULL,
  UPDATE_TIME             TIMESTAMP      NULL,
  PRIMARY KEY (FUNDCODE)
);


/*==============================================================*/
/* Table: FundNav_history                                       */
/*==============================================================*/
--Y
DROP TABLE IF EXISTS FUNDNAV_HISTORY;
CREATE TABLE FUNDNAV_HISTORY
(
  FUNDCODE                VARCHAR(30)    NOT NULL,
  ALIASCODE               VARCHAR(30)    NULL,
  FUNDNAME                TEXT           NULL,
  FUNDNAMEABBR            TEXT           NULL,
  CURR_DATE               TEXT           NULL,
  FUND_TYPE               INT            NULL,
  INVESTMENT_TYPE         INT            NULL,
  NETVALUE                DECIMAL(16, 5) NULL,
  TOTAL_NETVALUE          DECIMAL(16, 5) NULL,
  PERCENT                 DECIMAL(16, 5) NULL,
  INCOME_PER_TEN_THOUSAND DECIMAL(16, 5) NULL,
  PERCENT_SEVEN_DAYS      DECIMAL(16, 5) NULL,
  YIELD_1M                DECIMAL(16, 5) NULL,
  YIELD_3M                DECIMAL(16, 5) NULL,
  YIELD_6M                DECIMAL(16, 5) NULL,
  YIELD_12M               DECIMAL(16, 5) NULL,
  YIELD_THIS_YEAR         DECIMAL(16, 5) NULL,
  CGS_3_YEAR              DECIMAL(16, 5) NULL,
  LASTEST_TOTAL_ASSET     DECIMAL(16, 5) NULL,
  ON_SALE                 INT            NULL,
  RISK_LEVEL              VARCHAR(50)    NULL,
  SHARE_TYPE              VARCHAR(50)    NULL,
  PURCHASE_STATE          INT            NULL,
  SUBSCRIBE_STATE         INT            NULL,
  AIP_STATE               INT            NULL,
  RECOMMENDATION          INT            NULL,
  CHARGE_RATE_VALUE       DECIMAL(16, 5) NULL,
  DISCOUNT                DECIMAL(16, 5) NULL,
  SALE_CHARGE_RATE_VALUE  DECIMAL(16, 5) NULL,
  IS_MONETARY             INT            NULL,
  IS_STF                  INT            NULL,
  PURCHASE_LIMIT_MIN      DECIMAL(16, 5) NULL,
  REDEEM_LIMIT_MIN        DECIMAL(16, 5) NULL,
  RAPID_REDEEM            INT            NULL,
  IA_GUID                 VARCHAR(100)   NULL,
  FUND_MANAGEMENT_FEES    TEXT           NULL,
  FUND_TRUSTEE_FEES       TEXT           NULL,
  CREATE_TIME             TIMESTAMP      NULL,
  UPDATE_TIME             TIMESTAMP      NULL
);

--Y
/*==============================================================*/
/* Table: p_product_manage                                      */
/*==============================================================*/
DROP TABLE IF EXISTS P_PRODUCT_MANAGE;
CREATE TABLE P_PRODUCT_MANAGE
(
  ID                    BIGINT       NOT NULL,
  PRODUCT_CODE          VARCHAR(30)  NULL,
  PRODUCT_NAME          VARCHAR(100) NULL,
  PRODUCT_TYPE          VARCHAR(50)  NULL,
  PRODUCT_STATUS        VARCHAR(50)  NULL,
  BEGIN_DATE            TIMESTAMP    NULL,
  END_DATE              TIMESTAMP    NULL,
  TEMP_STOP_DATE        TIMESTAMP    NULL,
  PRIORITY_LEVEL        INT          NULL,
  RECOMMEND_TYPE        VARCHAR(50)  NULL,
  RECOMMEND_FLAG        VARCHAR(50)  NULL,
  RECOMMEND_DESC        VARCHAR(50)  NULL,
  SUPPLIER_CODE         VARCHAR(20)  NULL,
  URL                   VARCHAR(300) NULL,
  IS_GRAB               VARCHAR(50)  NULL,
  UP_BEGIN_TIME         TIMESTAMP    NULL,
  DOWN_END_TIME         TIMESTAMP    NULL,
  PRODUCT_DESC          VARCHAR(100) NULL,
  INIT_BUYED_COUNT      INT          NULL,
  ONE_MONTH_BUYED_COUNT INT          NULL,
  CREATE_TIME           TIMESTAMP    NULL,
  UPDATE_TIME           TIMESTAMP    NULL,
  CREATE_BY             VARCHAR(20)  NULL,
  UPDATE_BY             VARCHAR(20)  NULL,
  PRIMARY KEY (ID)
);

--Y
/*==============================================================*/
/* TABLE: CODE                                                  */
/*==============================================================*/
DROP TABLE IF EXISTS CODE;
CREATE TABLE CODE
(
  ID       BIGINT      NOT NULL,
  CATEGORY VARCHAR(10) NULL,
  CODE     VARCHAR(10) NULL,
  VALUE    VARCHAR(20) NULL,
  "DESC"   VARCHAR(50) NULL,
  CONSTRAINT PK_CODE PRIMARY KEY (ID)
);

-- Logback: the reliable, generic, fast and flexible logging framework.
-- Copyright (C) 1999-2010, QOS.ch. All rights reserved.
--
-- See http://logback.qos.ch/license.html for the applicable licensing
-- conditions.

-- This SQL script creates the required tables by ch.qos.logback.classic.db.DBAppender
--
-- It is intended for PostgreSQL databases.

--Y
DROP TABLE    logging_event_property;
DROP TABLE    logging_event_exception;
DROP TABLE    logging_event;
DROP SEQUENCE logging_event_id_seq;


CREATE SEQUENCE logging_event_id_seq MINVALUE 1 START 1;


CREATE TABLE logging_event
(
  timestmp         BIGINT NOT NULL,
  formatted_message  TEXT NOT NULL,
  logger_name       VARCHAR(254) NOT NULL,
  level_string      VARCHAR(254) NOT NULL,
  thread_name       VARCHAR(254),
  reference_flag    SMALLINT,
  arg0              VARCHAR(254),
  arg1              VARCHAR(254),
  arg2              VARCHAR(254),
  arg3              VARCHAR(254),
  caller_filename   VARCHAR(254) NOT NULL,
  caller_class      VARCHAR(254) NOT NULL,
  caller_method     VARCHAR(254) NOT NULL,
  caller_line       CHAR(4) NOT NULL,
  event_id          BIGINT DEFAULT nextval('logging_event_id_seq') PRIMARY KEY
);

CREATE TABLE logging_event_property
(
  event_id	      BIGINT NOT NULL,
  mapped_key        VARCHAR(254) NOT NULL,
  mapped_value      VARCHAR(1024),
  PRIMARY KEY(event_id, mapped_key),
  FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
);

CREATE TABLE logging_event_exception
(
  event_id         BIGINT NOT NULL,
  i                SMALLINT NOT NULL,
  trace_line       VARCHAR(254) NOT NULL,
  PRIMARY KEY(event_id, i),
  FOREIGN KEY (event_id) REFERENCES logging_event(event_id)
);

DROP TABLE    m_logging_event_property;
DROP TABLE    m_logging_event_exception;
DROP TABLE    m_logging_event;


ALTER TABLE fundnav ALTER COLUMN lastest_total_asset type numeric(18, 5);
ALTER TABLE fundnav_history ALTER COLUMN lastest_total_asset type numeric(18, 5);

CREATE SEQUENCE SRC;
CREATE TABLE
  P_PRODUCT
(
  id               INT8 NOT NULL,
  CREATED_BY       VARCHAR(30),
  CREATED_DATETIME TIMESTAMP,
  DEFUNCT_IND      VARCHAR(1),
  UPDATED_BY       VARCHAR(30),
  UPDATED_DATETIME TIMESTAMP,
  ISIN             VARCHAR(20),
  XGRQ             TIMESTAMP,
  CHI_NAME         VARCHAR(200),
  CHI_NAME_ABBR    VARCHAR(100),
  CHI_SPELLING     VARCHAR(10),
  COMPANY_CODE     INT4,
  ENG_NAME         VARCHAR(200),
  ENG_NAME_ABBR    VARCHAR(50),
  INNER_CODE       VARCHAR(10),
  LISTED_DATE      TIMESTAMP,
  LISTED_SECTOR    INT4,
  LISTED_STATE     INT4,
  SECU_ABBR        VARCHAR(20),
  SECU_CATEGORY    INT4,
  SECU_CODE        VARCHAR(10),
  SECU_MARKET      INT4,
  PRIMARY KEY (id)
);


CREATE TABLE
  P_FUND
(
  id                INT8           NOT NULL,
  CREATED_BY        VARCHAR(30),
  CREATED_DATETIME  TIMESTAMP,
  DEFUNCT_IND       VARCHAR(1),
  UPDATED_BY        VARCHAR(30),
  UPDATED_DATETIME  TIMESTAMP,
  FUND_CODE         VARCHAR(8)     NOT NULL,
  IS_APPLY          INT4           NOT NULL,
  IS_REDEMPTION     INT4           NOT NULL,
  LOWEST_REDEMPTION INT8           NOT NULL,
  MILLION_OF_PROFIT NUMERIC(18, 4) NOT NULL,
  MIN_APPLY_AMOUNT  NUMERIC(18, 4) NOT NULL,
  NAV_DATE          TIMESTAMP      NOT NULL,
  ONE_WEEK_PROFIT   NUMERIC(18, 4) NOT NULL,
  ONE_YEAR_PROFIT   NUMERIC(18, 4) NOT NULL,
  PRODUCT_STATUS    INT4           NOT NULL,
  PRODUCT_ID        INT8,
  PRIMARY KEY (id)
);
CREATE TABLE
  P_PRODUCT_RECOMMEND
(
  ID               BIGINT      NOT NULL,
  CREATED_BY       VARCHAR(30),
  CREATED_DATETIME TIMESTAMP,
  DEFUNCT_IND      VARCHAR(1),
  UPDATED_BY       VARCHAR(30),
  UPDATED_DATETIME TIMESTAMP,
  PRODUCT_ID       BIGINT      NULL,
  RECOMMEND_TYPE   VARCHAR(8)  NULL,
  PRODUCT_CODE     VARCHAR(8)  NULL,
  BEGIN_DATE       TIMESTAMP   NULL,
  END_DATE         TIMESTAMP   NULL,
  TEMP_STOP_DATE   TIMESTAMP   NULL,
  PRIORITY_LEVEL   INT         NULL,
  RECOMMEND_FLAG   INT         NULL,
  RECOMMEND_DESC   VARCHAR(50) NULL,
  CONSTRAINT PK_P_PRODUCT_RECOMMEND PRIMARY KEY (ID)
);

CREATE TABLE
  c_bank
(
  id               BIGINT NOT NULL,
  bank_code        CHARACTER VARYING(40),
  bank_name        CHARACTER VARYING(50),
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  en_name          CHARACTER VARYING(50),
  status           CHARACTER VARYING(1),
  update_datetime  TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT c_bank_pkey PRIMARY KEY (id)
);
CREATE TABLE
  c_bank_card
(
  id               BIGINT NOT NULL,
  bank_card_no     CHARACTER VARYING(40),
  bank_code        CHARACTER VARYING(40),
  status           CHARACTER VARYING(1),
  bank_type        CHARACTER VARYING(1),
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  customer_id      CHARACTER VARYING(30),
  update_datetime  TIMESTAMP WITHOUT TIME ZONE,
  bank_id          BIGINT,
  CONSTRAINT c_bank_card_pkey PRIMARY KEY (id)
);
CREATE TABLE
  c_customer
(
  id               BIGINT NOT NULL,
  created_by       CHARACTER VARYING(30),
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  customer_id      CHARACTER VARYING(30),
  customer_type    CHARACTER VARYING(1),
  device_no        CHARACTER VARYING(50),
  email            CHARACTER VARYING(50),
  identity_number  CHARACTER VARYING(30),
  identity_typer   CHARACTER VARYING(1),
  login_id         CHARACTER VARYING(20),
  login_password   CHARACTER VARYING(40),
  mobile           CHARACTER VARYING(11),
  nick_name        CHARACTER VARYING(20),
  pic_way          CHARACTER VARYING(40),
  property         CHARACTER VARYING(1),
  qq               CHARACTER VARYING(20),
  real_name        CHARACTER VARYING(20),
  referral_code    CHARACTER VARYING(10),
  reg_channel      CHARACTER VARYING(1),
  reg_way          CHARACTER VARYING(1),
  status           CHARACTER VARYING(1),
  updated_by       CHARACTER VARYING(30),
  updated_datetime TIMESTAMP WITHOUT TIME ZONE,
  weibo            CHARACTER VARYING(30),
  weixin           CHARACTER VARYING(30),
  CONSTRAINT c_customer_pkey PRIMARY KEY (id)
);
CREATE TABLE
  c_customer_gesture
(
  id               BIGINT NOT NULL,
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  device_no        CHARACTER VARYING(40),
  gesture_password CHARACTER VARYING(40),
  status           CHARACTER VARYING(1),
  updated_datetime TIMESTAMP WITHOUT TIME ZONE,
  customer_id      BIGINT,
  CONSTRAINT c_customer_gesture_pkey PRIMARY KEY (id),
  CONSTRAINT fk_o2tr6bha63v3n5moan54hcmdi FOREIGN KEY (customer_id) REFERENCES c_customer (id
  ) MATCH SIMPLE ON
  UPDATE
  NO ACTION
  ON
  DELETE
  NO ACTION
);
CREATE TABLE
  c_customer_session
(
  id               BIGINT NOT NULL,
  client_address   CHARACTER VARYING(40),
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  device_name      CHARACTER VARYING(40),
  device_no        CHARACTER VARYING(40),
  status           CHARACTER VARYING(1),
  token            CHARACTER VARYING(400),
  updated_datetime TIMESTAMP WITHOUT TIME ZONE,
  customer_id      BIGINT,
  CONSTRAINT c_customer_session_pkey PRIMARY KEY (id),
  CONSTRAINT fk_f1fy55k2g56akn7yy4t7wb18v FOREIGN KEY (customer_id) REFERENCES c_customer (id
  ) MATCH SIMPLE ON
  UPDATE
  NO ACTION
  ON
  DELETE
  NO ACTION
);
CREATE TABLE
  c_customer_verify_code
(
  id               BIGINT NOT NULL,
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  device_no        CHARACTER VARYING(40),
  mobile           CHARACTER VARYING(11),
  status           CHARACTER VARYING(1),
  updated_datetime TIMESTAMP WITHOUT TIME ZONE,
  verify_code      CHARACTER VARYING(40),
  verify_type      CHARACTER VARYING(10),
  CONSTRAINT c_customer_verify_code_pkey PRIMARY KEY (id)
);
CREATE TABLE
  f_basic_account
(
  id             BIGINT NOT NULL,
  balance        NUMERIC(19, 2),
  account_no     CHARACTER VARYING(255),
  create_time    TIMESTAMP WITHOUT TIME ZONE,
  delete_time    TIMESTAMP WITHOUT TIME ZONE,
  status         CHARACTER VARYING(255),
  trade_password CHARACTER VARYING(255),
  update_time    TIMESTAMP WITHOUT TIME ZONE,
  cust_id        CHARACTER(30),
  CONSTRAINT f_basic_account_pkey PRIMARY KEY (id)
);
CREATE TABLE
  login_history
(
  id               BIGINT NOT NULL,
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  device_no        CHARACTER VARYING(40),
  gesture_ind      CHARACTER VARYING(1),
  log_num          BIGINT,
  login_datetime   TIMESTAMP WITHOUT TIME ZONE,
  logout_datetime  TIMESTAMP WITHOUT TIME ZONE,
  pwd_ind          CHARACTER VARYING(1),
  social_ind       CHARACTER VARYING(1),
  success_ind      CHARACTER VARYING(1),
  updated_datetime TIMESTAMP WITHOUT TIME ZONE,
  customer_id      BIGINT,
  CONSTRAINT login_history_pkey PRIMARY KEY (id),
  CONSTRAINT fk_j1phrggv0s7j22odf5nemwe4t FOREIGN KEY (customer_id) REFERENCES c_customer (id
  ) MATCH SIMPLE ON
  UPDATE
  NO ACTION
  ON
  DELETE
  NO ACTION
);
CREATE TABLE
  parameter
(
  id               BIGINT NOT NULL,
  created_by       CHARACTER VARYING(30),
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  defunct_ind      CHARACTER VARYING(1),
  updated_by       CHARACTER VARYING(30),
  updated_datetime TIMESTAMP WITHOUT TIME ZONE,
  description      CHARACTER VARYING(255),
  name             CHARACTER VARYING(50),
  value            CHARACTER VARYING(50),
  CONSTRAINT parameter_pkey PRIMARY KEY (id)
);
CREATE TABLE
  sms_message
(
  id               BIGINT NOT NULL,
  content          CHARACTER VARYING(200),
  created_datetime TIMESTAMP WITHOUT TIME ZONE,
  mobile           CHARACTER VARYING(11),
  rec_status       CHARACTER VARYING(40),
  return_msg       CHARACTER VARYING(200),
  smsid            CHARACTER VARYING(40),
  updated_datetime TIMESTAMP WITHOUT TIME ZONE,
  CONSTRAINT sms_message_pkey PRIMARY KEY (id)
);

ALTER TABLE c_customer_gesture DROP COLUMN customer_id;

ALTER TABLE c_customer_gesture ADD COLUMN customer_id CHARACTER(30);
COMMENT ON COLUMN c_customer_gesture.customer_id IS '客户号';


ALTER TABLE c_customer_session DROP COLUMN customer_id;

ALTER TABLE c_customer_session ADD COLUMN customer_id CHARACTER(30);
COMMENT ON COLUMN c_customer_session.customer_id IS '客户号';

ALTER TABLE login_history DROP COLUMN customer_id;

ALTER TABLE login_history ADD COLUMN customer_id CHARACTER(30);
COMMENT ON COLUMN login_history.customer_id IS '客户号';

INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (1, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (2, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname2', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (3, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname3', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (4, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname4', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (5, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname5', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (6, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname6', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (7, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname7', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (8, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname8', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (9, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname9', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (10, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname10', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (11, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname11', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (12, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname12', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (13, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname13', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (14, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname14', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (15, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname15', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (16, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname16', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (17, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname17', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (18, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname18', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (19, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname19', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (20, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname20', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);
INSERT INTO p_product (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, isin, xgrq, chi_name, chi_name_abbr, chi_spelling, company_code, eng_name, eng_name_abbr, inner_code, listed_date, listed_sector, listed_state, secu_abbr, secu_category, secu_code, secu_market)
VALUES (21, NULL, NULL, 'N', NULL, NULL, 'isin', '2014-10-21 00:00:00', 'chiname21', 'chiabbr', 'chi_s', 1, 'engname',
        'engnameabbr', 'i1', '2014-10-21 00:00:00', 0, 0, 's', 1, 's1', 1);


INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (1, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 1);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (2, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 2);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (3, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 3);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (4, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 4);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (5, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 5);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (6, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 6);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (7, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 7);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (8, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 8);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (9, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 9);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (10, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 10);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (11, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 11);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (12, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 12);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (13, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 13);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (14, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 14);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (15, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 15);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (16, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 16);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (17, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 17);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (18, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 18);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (19, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 19);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (20, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 20);
INSERT INTO p_fund (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, fund_code, is_apply, is_redemption, lowest_redemption, million_of_profit, min_apply_amount, nav_date, one_week_profit, one_year_profit, product_status, product_id)
VALUES (21, NULL, NULL, 'N', NULL, NULL, 'GYHB', 0, 0, 1, 1.2376, 1.0000, '2014-10-21 00:00:00', 0.0430, 0.0050, 1, 21);


INSERT INTO p_product_recommend (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, product_id, recommend_type, product_code, begin_date, end_date, temp_stop_date, priority_level, recommend_flag, recommend_desc)
VALUES
  (1, NULL, NULL, 'N', NULL, NULL, 1, '0', NULL, '2014-10-21 00:00:00', '2014-10-29 00:00:00', '2014-10-28 00:00:00',
   NULL, NULL, NULL);


INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (1, '2014-09-27 10:30:22', '2014-09-27 10:30:22', 'BOC', '中国银行', 'Bank of China', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status) VALUES
  (2, '2014-09-27 10:33:35', '2014-09-27 10:33:35', 'ICBC', '中国工商银行', 'Industrial and Commercial Bank of China', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (3, '2014-09-27 10:35:24', '2014-09-27 10:35:24', 'ABC', '中国农业银行', 'Agricultural Bank of China', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (4, '2014-09-27 10:38:15', '2014-09-27 10:38:15', 'CCB', '中国建设银行', 'China Construction Bank', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (5, '2014-09-27 10:44:28', '2014-09-27 10:44:28', 'BCM', '交通银行', 'BANK OF COMMUNICATIONS ', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (6, '2014-09-27 10:46:43', '2014-09-27 10:46:43', 'PSBC', '中国邮政储蓄银行', 'POSTAL SAVINGS BANK OF CHINA', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (7, '2014-09-27 10:52:21', '2014-09-27 10:52:21', 'CITIC', '中信银行', 'CHINA CITIC BANK', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (8, '2014-09-27 10:54:56', '2014-09-27 10:54:56', 'SPDB', '上海浦东发展银行', 'Shanghai Pudong Development Bank', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (9, '2014-09-27 10:56:31', '2014-09-27 10:56:31', 'CIB', '兴业银行', 'Industrial Bank', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (10, '2014-09-27 11:01:38', '2014-09-27 11:01:38', 'CMBC', '中国民生银行', 'China Minsheng Banking Corp', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (11, '2014-09-27 11:06:53', '2014-09-27 11:06:53', 'CEB', '中国光大银行', 'CHINA EVERBRIGHT BANK', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (12, '2014-09-27 11:09:09', '2014-09-27 11:09:09', 'PAB', '平安银行', 'Ping An Bank ', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (13, '2014-09-27 11:10:44', '2014-09-27 11:10:44', 'HXB', '华夏银行', 'Hua Xia Bank', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (14, '2014-09-27 11:13:10', '2014-09-27 11:13:10', 'BOB', '北京银行', 'Bank of Beijing ', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (15, '2014-09-27 11:15:13', '2014-09-27 11:15:13', 'CGB', '广东发展银行', 'China Guangfa Bank', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (16, '2014-09-27 11:17:00', '2014-09-27 11:17:00', 'BOS', '上海银行', 'Bank of Shanghai', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (17, '2014-09-27 11:23:26', '2014-09-27 11:23:26', 'EGB', '恒丰银行', 'Evergrowing Bank', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (18, '2014-09-27 11:30:24', '2014-09-27 11:30:24', 'BRCB', '北京农村商业银行', 'BEIJING RURAL COMMERCIAL BANK', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (19, '2014-09-27 11:32:53', '2014-09-27 11:32:53', 'CRCB', '重庆农村商业银行', 'CHONGQING RURAL COMMERCLAL BANK', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (20, '2014-09-27 11:36:08', '2014-09-27 11:36:08', 'CBB', '渤海银行', 'China Bohai Bank', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (21, '2014-09-27 11:40:34', '2014-09-27 11:40:34', 'SRCB', '上海农村商业银行', 'Shanghai Rural Commercial Bank', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (22, '2014-09-27 11:47:31', '2014-09-27 11:49:07', 'BON', '南京银行', 'BANK OF NANJING', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (23, '2014-09-27 11:21:09', '2014-09-27 11:49:25', 'BOJ', '江苏银行', 'BANK OF JIANGSU', 'Y');
INSERT INTO c_bank (id, created_datetime, update_datetime, bank_code, bank_name, en_name, status)
VALUES (24, '2014-09-27 10:48:48', '2014-09-27 12:04:58', 'CMB', '招商银行', 'China Merchants Bank', 'Y');


CREATE TABLE c_open_Account_Pact
(
  agreement_No          VARCHAR(20)  NOT NULL,
  sign_Channel          VARCHAR(10)  NULL,
  agreement_Start_Date  TIMESTAMP    NULL,
  agreement_End_Date    TIMESTAMP    NULL,
  agreement_Effect_Date TIMESTAMP    NULL,
  agreement_Expiry_Date TIMESTAMP    NULL,
  agreement_Status      VARCHAR(1)   NULL,
  sale_Property         VARCHAR(1)   NULL,
  user_Attribution      VARCHAR(1)   NULL,
  agreement_Name        VARCHAR(40)  NULL,
  agreement_File_Type   VARCHAR(10)  NULL,
  file_Name             VARCHAR(100) NULL,
  file_Path             VARCHAR(200) NULL,
  status                VARCHAR(1)   NULL,
  create_Date           TIMESTAMP    NULL,
  update_Date           TIMESTAMP    NULL,
  CONSTRAINT PK_C_OPEN_ACCOUNT_PACT PRIMARY KEY (agreement_No)
);

INSERT INTO c_open_account_pact (agreement_no, sign_channel, agreement_start_date, agreement_end_date, agreement_effect_date, agreement_expiry_date, agreement_status, sale_property, user_attribution, agreement_name, agreement_file_type, file_name, file_path, status, create_date, update_date)
VALUES ('0001', NULL, '2014-09-27 12:04:58', NULL, '2014-09-28 12:04:58', NULL, 'Y', NULL, NULL, '用户协议', NULL,
        'userinstructions.html', 'http://192.168.1.97:8888/www/', 'Y', '2014-09-27 12:04:58', '2014-09-27 12:04:58');
INSERT INTO c_open_account_pact (agreement_no, sign_channel, agreement_start_date, agreement_end_date, agreement_effect_date, agreement_expiry_date, agreement_status, sale_property, user_attribution, agreement_name, agreement_file_type, file_name, file_path, status, create_date, update_date)
VALUES ('0002', NULL, '2014-09-27 12:04:58', NULL, '2014-09-28 12:04:58', NULL, 'Y', NULL, NULL, '软件服务协议', NULL,
        'softwaremaintenance.html', 'http://192.168.1.97:8888/www/', 'Y', '2014-09-27 12:04:58', '2014-09-27 12:04:58');

--2014-10-30
-------------------------------------------------------------------------------------
CREATE TABLE p_product_manage
(
  id             BIGINT       NOT NULL,
  product_name   VARCHAR(10)  NULL,
  begin_time     TIMESTAMP    NULL,
  end_date       TIMESTAMP    NULL,
  create_time    TIMESTAMP    NULL,
  update_time    TIMESTAMP    NULL,
  product_desc   VARCHAR(100) NULL,
  product_status CHAR(1)      NULL,
  url            VARCHAR(300) NULL,
  product_code   VARCHAR(10)  NULL,
  CONSTRAINT PK_P_PRODUCT_MANAGE PRIMARY KEY (id)
);

CREATE TABLE P_Fund
(
  Id                    BIGINT         NOT NULL,
  min_Apply_Amount      DECIMAL(18, 4) NULL,
  lowest_Redemption     BIGINT         NULL,
  one_Year_Profit       DECIMAL(18, 4) NULL,
  million_Of_Profit     DECIMAL(18, 4) NULL,
  one_Week_Profit       DECIMAL(18, 4) NULL,
  nav_Date              TIMESTAMP      NULL,
  is_Apply              VARCHAR(50)    NULL,
  is_Redemption         VARCHAR(50)    NULL,
  Product_Status        INT            NULL,
  Deleted               BOOLEAN        NULL,
  Create_time           TIMESTAMP      NULL,
  Update_time           TIMESTAMP      NULL,
  Update_By             VARCHAR(10)    NULL,
  Create_By             VARCHAR(10)    NULL,
  Company_Name          VARCHAR(10)    NULL,
  Chi_Name              VARCHAR(100)   NULL,
  Chi_Name_Abbr         VARCHAR(50)    NULL,
  Eng_Name              VARCHAR(100)   NULL,
  Eng_Name_Abbr         VARCHAR(50)    NULL,
  Secu_Abbr             VARCHAR(20)    NULL,
  fund_Scale            DECIMAL        NULL,
  fund_Type             VARCHAR(50)    NULL,
  invest_Period         VARCHAR(50)    NULL,
  charge                DECIMAL        NULL,
  to_Account_Type       VARCHAR(50)    NULL,
  supplier_Id           BIGINT         NULL,
  risk_level            VARCHAR(50)    NULL,
  fund_code             VARCHAR(10)    NULL,
  init_buyed_count      BIGINT         NULL,
  one_month_buyed_count BIGINT         NULL,
  CONSTRAINT PK_P_FUND PRIMARY KEY (Id)
);

CREATE TABLE P_Fund_HISTORY
(
  Id                    BIGINT         NOT NULL,
  min_Apply_Amount      DECIMAL(18, 4) NULL,
  lowest_Redemption     BIGINT         NULL,
  one_Year_Profit       DECIMAL(18, 4) NULL,
  million_Of_Profit     DECIMAL(18, 4) NULL,
  one_Week_Profit       DECIMAL(18, 4) NULL,
  nav_Date              TIMESTAMP      NULL,
  is_Apply              VARCHAR(50)    NULL,
  is_Redemption         VARCHAR(50)    NULL,
  Product_Status        INT            NULL,
  Deleted               BOOLEAN        NULL,
  Create_time           TIMESTAMP      NULL,
  Update_time           TIMESTAMP      NULL,
  Update_By             VARCHAR(10)    NULL,
  Create_By             VARCHAR(10)    NULL,
  Company_Name          VARCHAR(10)    NULL,
  Chi_Name              VARCHAR(100)   NULL,
  Chi_Name_Abbr         VARCHAR(50)    NULL,
  Eng_Name              VARCHAR(100)   NULL,
  Eng_Name_Abbr         VARCHAR(50)    NULL,
  Secu_Abbr             VARCHAR(20)    NULL,
  fund_Scale            DECIMAL        NULL,
  fund_Type             VARCHAR(50)    NULL,
  invest_Period         VARCHAR(50)    NULL,
  charge                DECIMAL        NULL,
  to_Account_Type       VARCHAR(50)    NULL,
  supplier_Id           BIGINT         NULL,
  risk_level            VARCHAR(50)    NULL,
  fund_code             VARCHAR(10)    NULL,
  init_buyed_count      BIGINT         NULL,
  one_month_buyed_count BIGINT         NULL,
  CONSTRAINT PK_P_Fund_HISTORY PRIMARY KEY (Id)
);
CREATE TABLE P_PRODUCT_RECOMMEND
(
  ID             BIGINT       NOT NULL,
  RECOMMEND_TYPE VARCHAR(8)   NULL,
  PRODUCT_NAME   VARCHAR(100) NULL,
  product_code   VARCHAR(30)  NULL,
  BEGIN_DATE     TIMESTAMP    NULL,
  END_DATE       TIMESTAMP    NULL,
  TEMP_STOP_DATE TIMESTAMP    NULL,
  PRIORITY_LEVEL INT          NULL,
  RECOMMEND_FLAG VARCHAR(50)  NULL,
  RECOMMEND_DESC VARCHAR(50)  NULL,
  CONSTRAINT PK_P_PRODUCT_RECOMMEND PRIMARY KEY (Id)
);
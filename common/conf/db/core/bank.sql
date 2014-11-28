CREATE TABLE c_bank
(
  id bigint NOT NULL,
  bank_code character varying(40), -- 银行编码
  bank_name character varying(50), -- 银行名称
  created_datetime timestamp without time zone,
  en_name character varying(50), -- 英文名
  status character varying(1), -- 状态
  update_datetime timestamp without time zone,
  CONSTRAINT c_bank_pkey PRIMARY KEY (id)
);

COMMENT ON COLUMN c_bank.bank_code IS '银行编码';
COMMENT ON COLUMN c_bank.bank_name IS '银行名称';
COMMENT ON COLUMN c_bank.en_name IS '英文名';
COMMENT ON COLUMN c_bank.status IS '状态';


CREATE TABLE c_bank_card
(
  id bigint NOT NULL,
  bank_card_no character varying(40), -- 银行卡号
  bank_code character varying(40), -- 银行编码
  status character varying(1),
  bank_type character varying(1), -- 卡类型
  created_datetime timestamp without time zone,
  customer_id character varying(30), -- 客户号
  update_datetime timestamp without time zone,
  bank_id character(40), -- 银行id
  bank_serial character varying(100),-- 银行序列号
  CONSTRAINT c_bank_card_pkey PRIMARY KEY (id)
);
COMMENT ON COLUMN c_bank_card.bank_card_no IS '银行卡号';
COMMENT ON COLUMN c_bank_card.bank_code IS '银行编码';
COMMENT ON COLUMN c_bank_card.bank_type IS '卡类型';
COMMENT ON COLUMN c_bank_card.customer_id IS '客户号';
COMMENT ON COLUMN c_bank_card.bank_id IS '银行id';
COMMENT ON COLUMN c_bank_card.bank_serial IS '银行序列号';
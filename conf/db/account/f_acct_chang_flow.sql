CREATE TABLE f_acct_chang_flow
(
  id bigint NOT NULL, -- 账户变动流水ID
  product_code character varying(8), -- 产品编码
  trade_no character varying(20), -- 交易流水号
  subject_no character varying(6), -- 科目号
  amount numeric(18,4), -- 变动金额
  create_time timestamp without time zone, -- 创建时间
  customer_id character varying(30), -- 客户号
  CONSTRAINT pk_f_acct_chang_flow PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE f_acct_chang_flow
  IS '账户变动流水表';
COMMENT ON COLUMN f_acct_chang_flow.id IS '账户变动流水ID';
COMMENT ON COLUMN f_acct_chang_flow.product_code IS '产品编码';
COMMENT ON COLUMN f_acct_chang_flow.trade_no IS '交易流水号';
COMMENT ON COLUMN f_acct_chang_flow.subject_no IS '科目号';
COMMENT ON COLUMN f_acct_chang_flow.amount IS '变动金额';
COMMENT ON COLUMN f_acct_chang_flow.create_time IS '创建时间';
COMMENT ON COLUMN f_acct_chang_flow.customer_id IS '客户号';

CREATE TABLE f_basic_account
(
  id bigint NOT NULL, -- 基本账户Id
  account_no character varying(30), -- 基本账户号
  status character(1), -- N表示正常
  balance numeric(18,4), -- 余额
  create_time timestamp without time zone, -- 创建时间
  update_time timestamp without time zone, -- 修改时间
  delete_time timestamp without time zone, -- 删除时间
  trade_password character varying(50), -- 交易密码
  cust_id character varying(30), -- 客户号
  CONSTRAINT pk_f_basic_account PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE f_basic_account IS '基本账户';
COMMENT ON COLUMN f_basic_account.id IS '基本账户Id';
COMMENT ON COLUMN f_basic_account.account_no IS '基本账户号';
COMMENT ON COLUMN f_basic_account.status IS 'N表示正常';
COMMENT ON COLUMN f_basic_account.balance IS '余额';
COMMENT ON COLUMN f_basic_account.create_time IS '创建时间';
COMMENT ON COLUMN f_basic_account.update_time IS '修改时间';
COMMENT ON COLUMN f_basic_account.delete_time IS '删除时间';
COMMENT ON COLUMN f_basic_account.trade_password IS '交易密码';
COMMENT ON COLUMN f_basic_account.cust_id IS '客户号';
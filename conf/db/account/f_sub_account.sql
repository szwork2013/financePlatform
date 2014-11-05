CREATE TABLE f_sub_account
(
  id bigint NOT NULL, -- 子账户ID
  sub_account character varying(10), -- 子账户号
  basic_account bigint, -- 基本账户号
  status character(1), -- 子账户状态
  balance numeric(18,4), -- 余额
  profit numeric(18,4), -- 累计收益
  yesterday_profit numeric(18,4), -- 昨日收益
  create_time timestamp without time zone, -- 创建时间
  update_time timestamp without time zone, -- 修改时间
  delete_time timestamp without time zone, -- 删除时间
  cust_id character(30), -- 客户号
  CONSTRAINT pk_f_sub_account PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE f_sub_account
  IS '子账户';
COMMENT ON COLUMN f_sub_account.id IS '子账户ID';
COMMENT ON COLUMN f_sub_account.sub_account IS '子账户号';
COMMENT ON COLUMN f_sub_account.basic_account IS '基本账户号';
COMMENT ON COLUMN f_sub_account.status IS '子账户状态';
COMMENT ON COLUMN f_sub_account.balance IS '余额';
COMMENT ON COLUMN f_sub_account.profit IS '累计收益';
COMMENT ON COLUMN f_sub_account.yesterday_profit IS '昨日收益';
COMMENT ON COLUMN f_sub_account.create_time IS '创建时间';
COMMENT ON COLUMN f_sub_account.update_time IS '修改时间';
COMMENT ON COLUMN f_sub_account.delete_time IS '删除时间';
COMMENT ON COLUMN f_sub_account.cust_id IS '客户号';

CREATE TABLE f_holdcapital
(
  id bigint NOT NULL,
  cust_id character varying(30), -- 客户号
  hold_capital numeric(18,4), -- 持有资产
  product_code character varying(8),
  product_name character varying(100),
  product_type character varying(50), -- 产品类型
  total_profit numeric(18,4), -- 累计收益
  trade_amount numeric(18,4), -- 申赎资产
  yesterday_profit numeric(18,4), -- 昨日收益
  status character varying(1),
  settle_date timestamp without time zone, -- 结算日期
  create_time timestamp without time zone,
  update_time timestamp without time zone,
  delete_time timestamp without time zone,
  CONSTRAINT f_holdcapital_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN f_holdcapital.cust_id IS '客户号';
COMMENT ON COLUMN f_holdcapital.hold_capital IS '持有资产';
COMMENT ON COLUMN f_holdcapital.settle_date IS '结算日期';
COMMENT ON COLUMN f_holdcapital.total_profit IS '累计收益';
COMMENT ON COLUMN f_holdcapital.trade_amount IS '申赎资产';
COMMENT ON COLUMN f_holdcapital.yesterday_profit IS '昨日收益';
COMMENT ON COLUMN f_holdcapital.product_type IS '产品类型';

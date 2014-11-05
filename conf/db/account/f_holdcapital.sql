
CREATE TABLE f_holdcapital
(
  id bigint NOT NULL,
  create_time timestamp without time zone,
  cust_id character varying(255), -- 客户号
  delete_time timestamp without time zone,
  hold_capital numeric(18,4), -- 持有资产
  product_code character varying(8),
  product_name character varying(100),
  settle_date timestamp without time zone, -- 结算日期
  status character varying(1),
  total_profit numeric(18,4), -- 累计收益
  trade_amount numeric(18,4), -- 申赎资产
  update_time timestamp without time zone,
  yesterday_profit numeric(18,4), -- 昨日收益
  product_type character varying(50), -- 产品类型
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

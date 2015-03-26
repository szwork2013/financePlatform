-- Table: t_trade

-- DROP TABLE t_trade;

CREATE TABLE t_trade
(
  id bigint NOT NULL, -- 交易主键
  trade_no character varying(100), -- 交易流水号
  fee numeric(18,4), -- 手续费
  type character(50), -- 1:申购 2:赎回 3:分红
  trade_status character(50), -- 1：存/取钱中、2：存/取钱成功、3：存/取失败【失败原因】
  confirm_status character(50), -- 0-不需发送；1-待确认；2-待确认；3-部分确认；4-确认完成；5-确认失败
  trade_time timestamp without time zone, -- 下单时间
  create_time timestamp without timetimee, -- 创建时间
  update_time timestamp without timeInfotime- 修改时间
  delete_time timestamp without timeInfo zone, -- 删除时间
  cust_id character varying(30), -- 客户号
  bank_card_no character varying(40), -- 银行卡号
  bank_name character varying(50), -- 银行名称
  pay_status character varying(50),
  trade_amount numeric(18,4), -- 申赎金额
  product_code character varying(8), -- 产品编码
  product_name character varying(100), -- 产品名称
  product_price numeric(18,4), -- 产品单价
  quantity integer, -- 数量
  holdcapital_id bigint,
  CONSTRAINT pk_t_trade PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE t_trade
  IS '交易表';
COMMENT ON COLUMN t_trade.id IS '交易主键';
COMMENT ON COLUMN t_trade.trade_no IS '交易流水号';
COMMENT ON COLUMN t_trade.fee IS '手续费';
COMMENT ON COLUMN t_trade.type IS '1:申购 2:赎回 3:分红
';
COMMENT ON COLUMN t_trade.trade_status IS '1：存/取钱中、2：存/取钱成功、3：存/取失败【失败原因】
';
COMMENT ON COLUMN t_trade.confirm_status IS '0-不需发送；1-待确认；2-待确认；3-部分确认；4-确认完成；5-确认失败
';
COMMENT ON COLUMN t_trade.trade_time IS '下单时间';
COMMENT ON COLUMN t_trade.create_time IS '创建时间';
COMMENT ON COLUMN t_trade.update_time IS '修改时间';
COMMENT ON COLUMN t_trade.delete_time IS '删除时间';
COMMENT ON COLUMN t_trade.cust_id IS '客户号';
COMMENT ON COLUMN t_trade.bank_card_no IS '银行卡号';
COMMENT ON COLUMN t_trade.bank_name IS '银行名称';
COMMENT ON COLUMN t_trade.trade_amount IS '申赎金额';
COMMENT ON COLUMN t_trade.product_code IS '产品编码';
COMMENT ON COLUMN t_trade.product_name IS '产品名称';
COMMENT ON COLUMN t_trade.product_price IS '产品单价';
COMMENT ON COLUMN t_trade.quantity IS '数量';


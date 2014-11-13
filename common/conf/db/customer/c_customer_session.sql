CREATE TABLE c_customer_session
(
  id bigint NOT NULL,
  client_address character varying(40), -- 客户端Ip地址
  create_time timestamp without time zone,
  device_name character varying(40), -- 设备名称
  device_no character varying(40), -- 设备号
  status character varying(1), -- Y失效 N有效
  token character varying(400),
  update_time timestamp without time zone,
  customer_id character varying(30), -- 客户号
  CONSTRAINT c_customer_session_pkey PRIMARY KEY (id),
);
COMMENT ON COLUMN c_customer_session.client_address IS '客户端Ip地址';
COMMENT ON COLUMN c_customer_session.device_name IS '设备名称';
COMMENT ON COLUMN c_customer_session.device_no IS '设备号';
COMMENT ON COLUMN c_customer_session.status IS 'Y失效 N有效';
COMMENT ON COLUMN c_customer_session.customer_id IS '客户号';
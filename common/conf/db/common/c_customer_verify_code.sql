CREATE TABLE c_customer_verify_code
(
  id bigint NOT NULL,
  create_time timestamp without time zone,
  device_no character varying(40), -- 设备号
  mobile character varying(11), -- 手机号
  status character varying(1), -- Y失效 N有效
  update_time timestamp without time zone,
  verify_code character varying(40), -- 验证码
  verify_type character varying(20), -- 类型
  CONSTRAINT c_customer_verify_code_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE c_customer_verify_code
  IS '验证码';
COMMENT ON COLUMN c_customer_verify_code.device_no IS '设备号';
COMMENT ON COLUMN c_customer_verify_code.mobile IS '手机号';
COMMENT ON COLUMN c_customer_verify_code.status IS 'Y失效 N有效';
COMMENT ON COLUMN c_customer_verify_code.verify_code IS '验证码';
COMMENT ON COLUMN c_customer_verify_code.verify_type IS '类型';


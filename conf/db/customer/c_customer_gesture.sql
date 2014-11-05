
CREATE TABLE c_customer_gesture
(
  id bigint NOT NULL,
  created_datetime timestamp without time zone, -- 创建时间
  device_no character varying(40), -- 设备号
  gesture_password character varying(40), -- 手势密码
  status character varying(1), -- Y失效 N有效
  updated_datetime timestamp without time zone, -- 更新时间
  customer_id character varying(30), -- 客户号
  CONSTRAINT c_customer_gesture_pkey PRIMARY KEY (id),
);
COMMENT ON COLUMN c_customer_gesture.created_datetime IS '创建时间';
COMMENT ON COLUMN c_customer_gesture.device_no IS '设备号';
COMMENT ON COLUMN c_customer_gesture.gesture_password IS '手势密码';
COMMENT ON COLUMN c_customer_gesture.status IS 'Y失效 N有效';
COMMENT ON COLUMN c_customer_gesture.updated_datetime IS '更新时间';
COMMENT ON COLUMN c_customer_gesture.customer_id IS '客户主键';

ALTER TABLE c_customer_gesture DROP COLUMN updated_datetime;

ALTER TABLE c_customer_gesture ADD COLUMN update_time timestamp without time zone;
COMMENT ON COLUMN c_customer_gesture.update_time IS '更新时间';

ALTER TABLE c_customer_gesture DROP COLUMN created_datetime;

ALTER TABLE c_customer_gesture ADD COLUMN create_time timestamp without time zone;
COMMENT ON COLUMN c_customer_gesture.create_time IS '创建时间';


ALTER TABLE c_customer_session DROP COLUMN created_datetime;

ALTER TABLE c_customer_session ADD COLUMN create_time timestamp without time zone;

ALTER TABLE c_customer_session DROP COLUMN updated_datetime;

ALTER TABLE c_customer_session ADD COLUMN update_time timestamp without time zone;


ALTER TABLE c_customer_verify_code DROP COLUMN created_datetime;

ALTER TABLE c_customer_verify_code ADD COLUMN create_time timestamp without time zone;

ALTER TABLE c_customer_verify_code DROP COLUMN updated_datetime;

ALTER TABLE c_customer_verify_code ADD COLUMN update_time timestamp without time zone;



ALTER TABLE login_history DROP COLUMN created_datetime;

ALTER TABLE login_history ADD COLUMN create_time timestamp without time zone;

ALTER TABLE login_history DROP COLUMN login_datetime;

ALTER TABLE login_history ADD COLUMN login_time timestamp without time zone;
COMMENT ON COLUMN login_history.login_time IS '登录时间';

 ALTER TABLE login_history DROP COLUMN logout_datetime;

ALTER TABLE login_history ADD COLUMN logout_time timestamp without time zone;
COMMENT ON COLUMN login_history.logout_time IS '登出时间';


ALTER TABLE login_history DROP COLUMN updated_datetime;

ALTER TABLE login_history ADD COLUMN update_time timestamp without time zone;



ALTER TABLE parameter DROP COLUMN created_by;
ALTER TABLE parameter DROP COLUMN created_datetime;
ALTER TABLE parameter DROP COLUMN updated_by;
ALTER TABLE parameter DROP COLUMN updated_datetime;
ALTER TABLE parameter ADD COLUMN status character varying(1);
COMMENT ON COLUMN parameter.status IS 'Y有效 N失效';




ALTER TABLE sms_message DROP COLUMN created_datetime;

ALTER TABLE sms_message ADD COLUMN create_time timestamp without time zone;


ALTER TABLE sms_message DROP COLUMN updated_datetime;

ALTER TABLE sms_message ADD COLUMN update_time timestamp without time zone;




ALTER TABLE c_customer DROP COLUMN created_by;

ALTER TABLE c_customer ADD COLUMN create_by character varying(30);
COMMENT ON COLUMN c_customer.create_by IS '创建人';


ALTER TABLE c_customer DROP COLUMN created_datetime;

ALTER TABLE c_customer ADD COLUMN create_time timestamp without time zone;
COMMENT ON COLUMN c_customer.create_time IS '创建时间';


ALTER TABLE c_customer DROP COLUMN updated_by;

ALTER TABLE c_customer ADD COLUMN update_by character varying(30);



 ALTER TABLE c_customer DROP COLUMN update_time;

ALTER TABLE c_customer ADD COLUMN update_time timestamp without time zone;





DROP TABLE login_history;

CREATE TABLE c_login_history
(
  id bigint NOT NULL,
  device_no character varying(40), -- 设备号
  gesture_ind character varying(1), -- Y手势登录
  log_num bigint, -- 登录失败次数
  pwd_ind character varying(1), -- Y密码登录
  social_ind character varying(1),
  success_ind character varying(1), -- 成功登录标志
  customer_id character varying(30), -- 客户号
  create_time timestamp without time zone,
  login_time timestamp without time zone, -- 登录时间
  logout_time timestamp without time zone, -- 登出时间
  update_time timestamp without time zone,
  CONSTRAINT login_history_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE c_login_history
  OWNER TO sunlights;
COMMENT ON COLUMN c_login_history.device_no IS '设备号';
COMMENT ON COLUMN c_login_history.gesture_ind IS 'Y手势登录 ';
COMMENT ON COLUMN c_login_history.log_num IS '登录失败次数';
COMMENT ON COLUMN c_login_history.pwd_ind IS 'Y密码登录';
COMMENT ON COLUMN c_login_history.success_ind IS '成功登录标志';
COMMENT ON COLUMN c_login_history.customer_id IS '客户号';
COMMENT ON COLUMN c_login_history.login_time IS '登录时间';
COMMENT ON COLUMN c_login_history.logout_time IS '登出时间';




update parameter set status = 'Y';

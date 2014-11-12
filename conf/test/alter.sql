
 alter table c_customer_gesture rename column updated_datetime to update_time;
 alter table c_customer_gesture rename column created_datetime to create_time;


  alter table c_customer_session rename column created_datetime to create_time;
   alter table c_customer_session rename column updated_datetime to update_time;


  alter table c_customer_verify_code rename column created_datetime to create_time;
   alter table c_customer_verify_code rename column updated_datetime to update_time;




ALTER TABLE parameter DROP COLUMN created_by;
ALTER TABLE parameter DROP COLUMN created_datetime;
ALTER TABLE parameter DROP COLUMN updated_by;
ALTER TABLE parameter DROP COLUMN updated_datetime;
ALTER TABLE parameter ADD COLUMN status character varying(1);
COMMENT ON COLUMN parameter.status IS 'Y有效 N失效';
update parameter set status = 'Y';


alter table sms_message rename column created_datetime to create_time;
   alter table sms_message rename column updated_datetime to update_time;


alter table c_customer rename column created_datetime to create_time;
   alter table c_customer rename column updated_datetime to update_time;
 alter table c_customer rename column created_by to create_by;
   alter table c_customer rename column updated_by to update_by;




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

update f_basic_account set status='Y';

--2014-11-12
 ALTER TABLE C_BANK RENAME COLUMN CREATED_DATETIME TO CREATE_TIME;
 ALTER TABLE C_BANK RENAME COLUMN UPDATED_DATETIME TO UPDATE_TIME;

  ALTER TABLE C_BANK_CARD RENAME COLUMN CREATED_DATETIME TO CREATE_TIME;
 ALTER TABLE C_BANK_CARD RENAME COLUMN UPDATE_DATETIME TO UPDATE_TIME;

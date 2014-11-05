
CREATE TABLE login_history
(
  id bigint NOT NULL,
  created_datetime timestamp without time zone,
  device_no character varying(40), -- 设备号
  gesture_ind character varying(1), -- Y手势登录
  log_num bigint, -- 登录失败次数
  login_datetime timestamp without time zone, -- 登录时间
  logout_datetime timestamp without time zone, -- 登出时间
  pwd_ind character varying(1), -- Y密码登录
  social_ind character varying(1),
  success_ind character varying(1), -- 成功登录标志
  updated_datetime timestamp without time zone,
  customer_id character varying(30), -- 客户号
  CONSTRAINT login_history_pkey PRIMARY KEY (id),
);
COMMENT ON COLUMN login_history.device_no IS '设备号';
COMMENT ON COLUMN login_history.gesture_ind IS 'Y手势登录 ';
COMMENT ON COLUMN login_history.log_num IS '登录失败次数';
COMMENT ON COLUMN login_history.login_datetime IS '登录时间';
COMMENT ON COLUMN login_history.logout_datetime IS '登出时间';
COMMENT ON COLUMN login_history.pwd_ind IS 'Y密码登录';
COMMENT ON COLUMN login_history.success_ind IS '成功登录标志';
COMMENT ON COLUMN login_history.customer_id IS '客户主键';

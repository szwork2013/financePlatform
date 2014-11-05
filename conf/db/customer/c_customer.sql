CREATE TABLE c_customer
(
  id bigint NOT NULL,
  created_by character varying(30), -- 创建人
  created_datetime timestamp without time zone, -- 创建时间
  customer_id character varying(30), -- 客户号
  customer_type character varying(1), -- 客户类型
  device_no character varying(50), -- 注册设备号
  email character varying(50), -- 绑定邮箱
  identity_number character varying(30), -- 证件号码
  identity_typer character varying(1), -- 证件类型
  login_id character varying(20), -- 登陆号
  login_password character varying(40), -- 登陆密码
  mobile character varying(11), -- 绑定手机
  nick_name character varying(20), -- 昵称
  pic_way character varying(40), -- 用户图像存放路径
  property character varying(1), -- 用户属性
  qq character varying(20), -- 绑定QQ
  real_name character varying(20), -- 真实姓名
  referral_code character varying(10), -- 推荐码
  reg_channel character varying(1), -- 注册渠道
  reg_way character varying(1), -- 注册方式
  status character varying(1), -- 用户状态
  updated_by character varying(30),
  updated_datetime timestamp without time zone,
  weibo character varying(30), -- 绑定微博号
  weixin character varying(30), -- 绑定微信号
  CONSTRAINT c_customer_pkey PRIMARY KEY (id)
);
COMMENT ON COLUMN c_customer.created_by IS '创建人';
COMMENT ON COLUMN c_customer.created_datetime IS '创建时间';
COMMENT ON COLUMN c_customer.customer_id IS '客户号';
COMMENT ON COLUMN c_customer.customer_type IS '客户类型';
COMMENT ON COLUMN c_customer.device_no IS '注册设备号';
COMMENT ON COLUMN c_customer.email IS '绑定邮箱';
COMMENT ON COLUMN c_customer.identity_number IS '证件号码';
COMMENT ON COLUMN c_customer.identity_typer IS '证件类型';
COMMENT ON COLUMN c_customer.login_id IS '登陆号';
COMMENT ON COLUMN c_customer.login_password IS '登陆密码';
COMMENT ON COLUMN c_customer.mobile IS '绑定手机';
COMMENT ON COLUMN c_customer.nick_name IS '昵称';
COMMENT ON COLUMN c_customer.pic_way IS '用户图像存放路径';
COMMENT ON COLUMN c_customer.property IS '用户属性';
COMMENT ON COLUMN c_customer.qq IS '绑定QQ';
COMMENT ON COLUMN c_customer.real_name IS '真实姓名';
COMMENT ON COLUMN c_customer.referral_code IS '推荐码';
COMMENT ON COLUMN c_customer.reg_channel IS '注册渠道';
COMMENT ON COLUMN c_customer.reg_way IS '注册方式';
COMMENT ON COLUMN c_customer.status IS '用户状态';
COMMENT ON COLUMN c_customer.weibo IS '绑定微博号';
COMMENT ON COLUMN c_customer.weixin IS '绑定微信号';
CREATE TABLE parameter
(
  id bigint NOT NULL,
  created_by character varying(30), -- 创建人
  created_datetime timestamp without time zone, -- 创建时间
  defunct_ind character varying(1), -- N有效 Y失效
  updated_by character varying(30), -- 更新人
  updated_datetime timestamp without time zone, -- 更新时间
  description character varying(255), -- 描述
  name character varying(50), -- 参数名
  value character varying(50), -- 参数值
  CONSTRAINT parameter_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN parameter.created_by IS '创建人';
COMMENT ON COLUMN parameter.created_datetime IS '创建时间';
COMMENT ON COLUMN parameter.defunct_ind IS 'N有效 Y失效';
COMMENT ON COLUMN parameter.updated_by IS '更新人';
COMMENT ON COLUMN parameter.updated_datetime IS '更新时间';
COMMENT ON COLUMN parameter.description IS '描述';
COMMENT ON COLUMN parameter.name IS '参数名';
COMMENT ON COLUMN parameter.value IS '参数值';




insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (1, null, '2014-09-22 11:47:15', 'N', null, '2014-09-22 11:47:15', '实名认证-URL地址', 'CERTIFY_URL', 'http://service.sfxxrz.com/IdentifierService.svc');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (2, null, '2014-09-22 11:46:50', 'N', null, '2014-09-22 11:46:50', '实名认证-帐号密码', 'CERTIFY_PASSWORD', '0DEi9dPb');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (3, null, '2014-09-23 12:13:14', 'N', null, '2014-09-23 12:13:14', '缓存存在时间(min)', 'CACHE_EXPIRY', '60');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (4, null, '2014-09-15 18:28:41', 'N', null, '2014-09-15 18:28:41', '登录失败没到最大次数，隔XXX时间后失败次数清0的时间(min)', 'LOGIN_PERIOD', '60');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (5, null, '2014-09-15 18:27:11', 'N', null, '2014-09-15 18:27:11', '验证码在单位时间内最大次数', 'VERIFYCODE_MAX', '5');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (6, null, '2014-09-15 18:28:27', 'N', null, '2014-09-15 18:28:27', '密码允许错误最大次数', 'PWD_MAX', '5');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (7, null, '2014-09-15 18:28:13', 'N', null, '2014-09-15 18:28:13', '验证码时效时间(min)', 'VERIFYCODE_EXPIRY', '60');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (8, null, '2014-09-22 11:46:26', 'N', null, '2014-09-22 11:46:26', '实名认证-帐号用户名', 'CERTIFY_USERNAME', 'yyzc_admin');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (9, null, '2014-09-15 18:28:51', 'N', null, '2014-09-15 18:28:51', '用户解锁时间(min)', 'USERUNLOCK_PERIOD', '60');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (10, null, '2014-09-15 18:27:58', 'N', null, '2014-09-15 18:27:58', '验证码最大次数的单位时间(min)', 'VERIFYCODE_TIMES', '60');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (11, null, '2014-09-25 11:36:18', 'N', null, '2014-09-25 11:36:18', '实名认证-N真实调用/非N测试模式', 'CERTIFY_TEST', 'Y');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (12, null, '2014-09-18 10:26:36', 'N', null, '2014-09-18 10:26:36', '暂时锁定的时间(min)', 'RELIEVE_SUSLOCK_PERIOD', '30');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (13, null, '2014-09-23 12:13:33', 'N', null, '2014-09-23 12:13:33', 'cookie存在时间(min)', 'COOKIE_EXPIRY', '1440');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (14, null, '2014-09-23 12:13:48', 'N', null, '2014-09-23 12:13:48', '后台CustomerSession有效时间(min)，一般与COOKIE_EXPIRY存在时间一致', 'SESSION_EXPIRY', '1440');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (15, null, '2014-10-15 19:48:37', 'N', null, '2014-10-15 19:48:37', '短信接口-URL地址', 'SMS_URL', 'http://sms.2office.net:8080/WebService/sms3.aspx');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (16, null, '2014-10-15 19:48:58', 'N', null, '2014-10-15 19:48:58', '短信接口-第二办公室门牌号码', 'SMS_ACCOUNT', '2523040');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (17, null, '2014-10-15 19:49:20', 'N', null, '2014-10-15 19:49:20', '短信接口-密码', 'SMS_PASSWORD', 'yiyuezc597');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (18, null, '2014-10-15 19:49:41', 'N', null, '2014-10-15 19:49:41', '短信接口-通道编号', 'SMS_CHANNEL', '252304001');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (19, null, '2014-10-15 19:50:00', 'N', null, '2014-10-15 19:50:00', '短信接口-授权码', 'SMS_WARRANTYCODE', '9a15294089130ec6a8d27502d808a2a1');
insert into parameter (id, created_by, created_datetime, defunct_ind, updated_by, updated_datetime, description, name, value) values (20, null, '2014-10-15 19:53:54', 'N', null, '2014-10-15 19:53:54', '短信接口-N真实调用/非N 测试模式', 'SMS_TEST', 'N');

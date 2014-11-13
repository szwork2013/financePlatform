CREATE TABLE parameter
(
  id bigint NOT NULL,
  status character varying(1), -- N失效 Y有效
  description character varying(255), -- 描述
  name character varying(50), -- 参数名
  value character varying(50), -- 参数值
  CONSTRAINT parameter_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN parameter.status IS 'N失效 Y有效';
COMMENT ON COLUMN parameter.description IS '描述';
COMMENT ON COLUMN parameter.name IS '参数名';
COMMENT ON COLUMN parameter.value IS '参数值';




insert into parameter (id, status, description, name, value) values (2, 'Y', '实名认证-帐号密码', 'CERTIFY_PASSWORD', '0DEi9dPb');
insert into parameter (id, status, description, name, value) values (4, 'Y', '登录失败没到最大次数，隔XXX时间后失败次数清0的时间(min)', 'LOGIN_PERIOD', '60');
insert into parameter (id, status, description, name, value) values (5, 'Y', '验证码在单位时间内最大次数', 'VERIFYCODE_MAX', '5');
insert into parameter (id, status, description, name, value) values (6, 'Y', '密码允许错误最大次数', 'PWD_MAX', '5');
insert into parameter (id, status, description, name, value) values (7, 'Y', '验证码时效时间(min)', 'VERIFYCODE_EXPIRY', '60');
insert into parameter (id, status, description, name, value) values (8, 'Y', '实名认证-帐号用户名', 'CERTIFY_USERNAME', 'yyzc_admin');
insert into parameter (id, status, description, name, value) values (9, 'Y', '用户解锁时间(min)', 'USERUNLOCK_PERIOD', '60');
insert into parameter (id, status, description, name, value) values (10, 'Y', '验证码最大次数的单位时间(min)', 'VERIFYCODE_TIMES', '60');
insert into parameter (id, status, description, name, value) values (11, 'Y', '实名认证-N真实调用/非N测试模式', 'CERTIFY_TEST', 'Y');
insert into parameter (id, status, description, name, value) values (12, 'Y', '暂时锁定的时间(min)', 'RELIEVE_SUSLOCK_PERIOD', '30');
insert into parameter (id, status, description, name, value) values (13, 'Y', 'cookie存在时间(min)', 'COOKIE_EXPIRY', '1440');
insert into parameter (id, status, description, name, value) values (14, 'Y', '后台CustomerSession有效时间(min)，一般与COOKIE_EXPIRY存在时间一致', 'SESSION_EXPIRY', '1440');
insert into parameter (id, status, description, name, value) values (15, 'Y', '短信接口-URL地址', 'SMS_URL', 'http://sms.2office.net:8080/WebService/sms3.aspx');
insert into parameter (id, status, description, name, value) values (16, 'Y', '短信接口-第二办公室门牌号码', 'SMS_ACCOUNT', '2523040');
insert into parameter (id, status, description, name, value) values (17, 'Y', '短信接口-密码', 'SMS_PASSWORD', 'yiyuezc597');
insert into parameter (id, status, description, name, value) values (18, 'Y', '短信接口-通道编号', 'SMS_CHANNEL', '252304001');
insert into parameter (id, status, description, name, value) values (19, 'Y', '短信接口-授权码', 'SMS_WARRANTYCODE', '9a15294089130ec6a8d27502d808a2a1');
insert into parameter (id, status, description, name, value) values (20, 'Y', '短信接口-N真实调用/非N 测试模式', 'SMS_TEST', 'N');
insert into parameter (id, status, description, name, value) values (1, 'Y', '实名认证-URL地址', 'CERTIFY_URL', 'http://service.sfxxrz.com/IdentifierService.svc');
insert into parameter (id, status, description, name, value) values (3, 'Y', '缓存存在时间(min)', 'CACHE_EXPIRY', '60');

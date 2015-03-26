insert into parameter (id, description, name, value, status) values (8, '实名认证-帐号用户名', 'CERTIFY_USERNAME', 'yyzc_admin', 'Y');
insert into parameter (id, description, name, value, status) values (9, '用户解锁时间(min)', 'USERUNLOCK_PERIOD', '60', 'Y');
insert into parameter (id, description, name, value, status) values (10, '验证码最大次数的单位时间(min)', 'VERIFYCODE_TIMES', '60', 'Y');
insert into parameter (id, description, name, value, status) values (11, '实名认证-N真实调用/非N测试模式', 'CERTIFY_TEST', 'Y', 'Y');
insert into parameter (id, description, name, value, status) values (12, '暂时锁定的时间(min)', 'RELIEVE_SUSLOCK_PERIOD', '30', 'Y');
insert into parameter (id, description, name, value, status) values (13, 'cookie存在时间(min)', 'COOKIE_EXPIRY', '1440', 'Y');
insert into parameter (id, description, name, value, status) values (14, '后台CustomerSession有效时间(min)，一般与COOKIE_EXPIRY存在时间一致', 'SESSION_EXPIRY', '1440', 'Y');
insert into parameter (id, description, name, value, status) values (15, '短信接口-URL地址', 'SMS_URL', 'http://sms.2office.net:8080/WebService/sms3.aspx', 'Y');
insert into parameter (id, description, name, value, status) values (16, '短信接口-第二办公室门牌号码', 'SMS_ACCOUNT', '2523040', 'Y');
insert into parameter (id, description, name, value, status) values (18, '短信接口-通道编号', 'SMS_CHANNEL', '252304001', 'Y');
insert into parameter (id, description, name, value, status) values (19, '短信接口-授权码', 'SMS_WARRANTYCODE', '9a15294089130ec6a8d27502d808a2a1', 'Y');
insert into parameter (id, description, name, value, status) values (20, '短信接口-N真实调用/非N 测试模式', 'SMS_TEST', 'N', 'Y');
insert into parameter (id, description, name, value, status) values (4, '登录失败没到最大次数，隔XXX时间后失败次数清0的时间(min)', 'LOGIN_PERIOD', '60', 'Y');
insert into parameter (id, description, name, value, status) values (7, '验证码时效时间(min)', 'VERIFYCODE_EXPIRY', '10', 'Y');
insert into parameter (id, description, name, value, status) values (32, '极光推送app_key', 'APP_KEY', 'b5763dd71f67ef2da3e08fa2', 'Y');
insert into parameter (id, description, name, value, status) values (17, '短信接口-密码', 'SMS_PASSWORD', 'yiyuezc597', 'Y');
insert into parameter (id, description, name, value, status) values (6, '密码允许错误最大次数', 'PWD_MAX', '5', 'Y');
insert into parameter (id, description, name, value, status) values (422461, '电信号段', 'CTCC', '^1(33|53|8[09])\d{8}$', 'Y');
insert into parameter (id, description, name, value, status) values (422462, '联通号段', 'CUCC', '^1(3[0-2]|45|5[56]|8[56])\d{8}$', 'Y');
insert into parameter (id, description, name, value, status) values (111, '移动号段', 'CMCC', '^1(34[0-8]|(3[5-9]|47|5[0-2]|57[124]|5[89]|8[2378])\d)\d{7}$', 'Y');
insert into parameter (id, description, name, value, status) values (33, '极光推送secret_key', 'SECRET_KEY', 'd5bc1fca3c38f30e212a5b85', 'Y');
insert into parameter (id, description, name, value, status) values (34, '活动静态资源服务器', 'ACTIVITY_SERVER', 'http://192.168.0.97', 'Y');
insert into parameter (id, description, name, value, status) values (35, '活动静态资源服务器端口', 'ACTIVITY_SERVER_PORT', '80', 'Y');
insert into parameter (id, description, name, value, status) values (36, '活动静态资源服务器上活动图片的位置', 'ACTIVITY_IMAGE_PATH', '/activity/images', 'Y');
insert into parameter (id, description, name, value, status) values (37, '活动静态资源服务器上活动html5的位置', 'ACTIVITY_HTML5_PATH', '/activity', 'Y');
insert into parameter (id, description, name, value, status) values (1, '实名认证-URL地址', 'CERTIFY_URL', 'http://service.sfxxrz.com/IdentifierService.svc', 'Y');
insert into parameter (id, description, name, value, status) values (2, '实名认证-帐号密码1', 'CERTIFY_PASSWORD', '0DEi9dPb', 'Y');
insert into parameter (id, description, name, value, status) values (3, '缓存存在时间(min)1', 'CACHE_EXPIRY', '60', 'Y');
insert into parameter (id, description, name, value, status) values (38, '安卓在应用商店里的最新版本', 'ANDROID_LATEST_VERSION', '1.4', 'Y');
insert into parameter (id, description, name, value, status) values (446524, 'test', 'test', 'test', 'Y');
insert into parameter (id, description, name, value, status) values (5, '验证码在单位时间内最大次数1111', 'VERIFYCODE_MAX', '5', 'Y');
insert into parameter (id, description, name, value, status) values (422464, '金豆兑换话费可兑换的份额列表，每份之间用；隔开', 'EXCHANGE_BEAN', '1;2;5;100;200;500', 'Y');
insert into parameter (id, description, name, value, status) values (4273, '2015年国定假日安排，每天之间用；隔开，格式yyyy-MM-dd', 'HOLIDAY_DATE', '2015-01-01;2015-01-02;2015-01-03;2015-02-18;2015-02-19;2015-02-20;2015-02-21;2015-02-22;2015-02-23;2015-02-24;2015-04-04;2015-04-05;2015-04-06;2015-05-01;2015-05-02;2015-05-03;2015-06-20;2015-06-21;2015-06-22;2015-09-26;2015-09-27;2015-10-01;2015-10-02;2015-10-03;2015-10-04;2015-10-05;2015-10-06;2015-10-07', 'Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.PURCHASE.SHOWINCOME','显示收益','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.PURCHASE.CONFIRMINCOME','确认份额并开始计算收益','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.PURCHASE.APPLY','购买申请提交','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.REDEEM.CONFIRMTIME','1~3个交易日后','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.REDEEM.CONFIRM','预计取现到银行卡','Y');
insert into parameter ( id, description, name, value, status) values ((SELECT nextval('SRC')),'待确认的交易描述信息','TRADE.REDEEM.APPLY','取现申请提交','Y');




alter table t_trade add constraint PK_trade_id primary key (id);


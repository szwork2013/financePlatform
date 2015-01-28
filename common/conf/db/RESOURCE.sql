INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (1, '首页', 'home', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (2, '系统管理', 'system', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (3, '财务管理', 'finance', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (4, '产品管理', 'product', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (5, '活动管理', 'activity', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (6, '消息管理', 'message', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (7, '问题反馈', 'feedback', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (8, '银行管理', 'bank', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (9, '数据统计', 'statistics', '1', null, 'menu', null, '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);

--system
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (10, '定时任务', 'system:job', '1', 2, 'menu', '/#/tasks', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (11, '日志管理', 'system:log', '2', 2, 'menu', '/#/logs', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (12, '基金公司管理', 'system:fund:company', '3', 2, 'menu', '/#/product/fund/companies', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (13, '供应商管理', 'system:supplier', '4', 2, 'menu', '/#/product/suppliers', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (14, '客户管理', 'system:customer', '5', 2, 'menu', '/#/user/customers', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (15, '验证码', 'system:verifyCode', '6', 2, 'menu', '/#/user/customer/verifycode', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (16, '字典表管理', 'system:dict', '7', 2, 'menu', '/#/dicts', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (17, '参数管理', 'system:parameter', '8', 2, 'menu', '/#/parameters', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (18, '清算管理', 'system:liquidation', '9', 2, 'menu', '/#/settleaccount', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);

--finance
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (19, '对账管理', 'finance:reconcile', '1', 3, 'menu', '/#/finance/reconcile', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (20, '结算统计', 'finance:statistics', '2', 3, 'menu', '/#/finance/statistics', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);

--product
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (21, '产品信息', 'product:manage', '1', 4, 'menu', '/#/product/manages', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (22, '基金信息', 'product:fund', '2', 4, 'menu', '/#/product/funds', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);

--activity
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (23, '活动场景', 'activity:scene', '1', 5, 'menu', '/#/activity/scenes', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (24, '奖励类型', 'activity:reward:type', '2', 5, 'menu', '/#/rewardTypes', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (25, '分享信息', 'activity:share', '3', 5, 'menu', '/#/activity/share', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (26, '活动列表', 'activity:index', '4', 5, 'menu', '/#/activities', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (27, '兑换场景', 'activity:exchange:scene', '5', 5, 'menu', '/#/exchangescene', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (28, '兑换结果', 'activity:exchange:result', '6', 5, 'menu', '/#/exchangeresults', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (29, '基金信息', 'activity:message', '7', 5, 'menu', '/#/activity/returnMsg', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);

--message
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (30, '短信统计', 'message:sms', '1', 6, 'menu', '/#/smsmessage', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (31, '群组管理', 'message:group', '2', 6, 'menu', '/#/group', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (32, '消息规则', 'message:role', '3', 6, 'menu', '/#/messpush', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (33, '消息推送计划配置', 'message:config', '4', 6, 'menu', '/#/messpushconfig', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (34, '规则与活动映射管理', 'message:mapping', '5', 6, 'menu', '/#/messpushmapping', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);

--feedback
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (35, '反馈', 'feedback:index', '1', 7, 'menu', '/#/feedbacks', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (36, '问题记录', 'feedback:question', '2', 7, 'menu', '/#/questionrecords', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);

--bank
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (37, '银行管理', 'bank:manage', '1', 8, 'menu', '/#/banks', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (38, '银行活期', 'bank:deposit', '2', 8, 'menu', '/#/deposit/interests', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);

--statistics
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (39, '汇总统计', 'statistics:summary', '1', 9, 'menu', '/#/statistics/customerInOutSummary', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (40, '推荐人统计', 'statistics:referrer', '2', 9, 'menu', '/#/statistics/recommends', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (41, '购买明细', 'statistics:purchase', '3', 9, 'menu', '/#/statistics/purchase', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);
INSERT INTO public."RESOURCE" (id, name, code, seq_no, parent_id, type, uri, create_time, update_time, deleted) VALUES (42, '注册未购买', 'statistics:notpurchase', '4', 9, 'menu', '/#/statistics/notpurchase', '2015-01-28 13:23:49.902', '2015-01-28 13:23:51.476', false);












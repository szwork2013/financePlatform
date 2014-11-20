DROP TABLE IF EXISTS F_EXCHANGE_REWARD_RULE;

/*==============================================================*/
/* Table: F_EXCHANGE_REWARD_RULE                                */
/*==============================================================*/
create table F_EXCHANGE_REWARD_RULE
(
   ID                   INT8                           not null,
   REWARD_TYPE          VARCHAR(6)                     null,
   STATUS               VARCHAR(2)                     null,
   EXCHAGE_TYPE         VARCHAR(6)                     null,
   RATE                 DECIMAL(18,4)                  not null,
   LIMIT_TIME           INT4                           null,
   NOTICE_TIME          INT4                           null,
   DELAY_TIME           INT4                           null,
   CREATE_TIME          TIMESTAMP                      null,
   UPDATE_TIME          TIMESTAMP                      null,
   CREATE_BY            VARCHAR(30)                    null,
   UPDATE_BY            VARCHAR(30)                    null,
   constraint PK_F_EXCHANGE_REWARD_RULE primary key (ID)
);

comment on column F_EXCHANGE_REWARD_RULE.ID is
'主键';

comment on column F_EXCHANGE_REWARD_RULE.REWARD_TYPE is
'奖励类型';

comment on column F_EXCHANGE_REWARD_RULE.STATUS is
'状态 N表示正常  F表示禁止使用';

comment on column F_EXCHANGE_REWARD_RULE.EXCHAGE_TYPE is
'兑换类型';

comment on column F_EXCHANGE_REWARD_RULE.RATE is
'兑换率';

comment on column F_EXCHANGE_REWARD_RULE.LIMIT_TIME is
'兑换期限  1-1个月；2-2个月；3-3个月 4-6个月 5-两周';

comment on column F_EXCHANGE_REWARD_RULE.NOTICE_TIME is
'兑换到期提醒  1-提前1星期通知 2-提前3天通知';

comment on column F_EXCHANGE_REWARD_RULE.DELAY_TIME is
'兑换期限延长 1-积分有效期延长1周 2-积分有效期延长1月，并分享活动';

comment on column F_EXCHANGE_REWARD_RULE.CREATE_TIME is
'创建时间';

comment on column F_EXCHANGE_REWARD_RULE.UPDATE_TIME is
'修改时间';

comment on column F_EXCHANGE_REWARD_RULE.CREATE_BY is
'创建人';

comment on column F_EXCHANGE_REWARD_RULE.UPDATE_BY is
'修改人';


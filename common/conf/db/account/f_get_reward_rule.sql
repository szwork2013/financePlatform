DROP TABLE IF EXISTS F_GET_REWARD_RULE;
/*==============================================================*/
/* Table: F_GET_REWARD_RULE                                     */
/*==============================================================*/
create table F_GET_REWARD_RULE 
(
   ID                   INT8                           not null,
   activity_id          INT8                           null,
   reward_type          VARCHAR(6)                     null,
   status               VARCHAR(2)                     not null,
   should_reward        INT8                           not null,
   real_reward          INT8                           null,
   back_reward          INT8                           null,
   effect_time          INT4                           null,
   valid_time           INT4                           null,
   total_limit_amt      INT8                           null,
   product_type         VARCHAR(30)                    null,
   product_code         VARCHAR(16)                    null,
   activity_channel     INT4                           null,
   trade_amt            NUMERIC(18,2)                  null,
   back_funds           NUMERIC(18,2)                  null,
   create_time          TIMESTAMP                      null,
   update_time          TIMESTAMP                      null,
   create_by            VARCHAR(30)                    null,
   update_by            VARCHAR(30)                    null,
   constraint PK_F_GET_REWARD_RULE primary key (ID)
);

comment on column F_GET_REWARD_RULE.ID is 
'主键';

comment on column F_GET_REWARD_RULE.activity_id is 
'活动Id';

comment on column F_GET_REWARD_RULE.reward_type is 
'奖励类型';

comment on column F_GET_REWARD_RULE.status is 
'状态  N表示正常  F表示禁用';

comment on column F_GET_REWARD_RULE.should_reward is 
'应发奖励';

comment on column F_GET_REWARD_RULE.real_reward is 
'实发奖励';

comment on column F_GET_REWARD_RULE.back_reward is 
'退回奖励';

comment on column F_GET_REWARD_RULE.effect_time is 
'奖励到账实效 1-实时到账 2-1到2个工作日 3-2到3个工作日 4-月末';

comment on column F_GET_REWARD_RULE.valid_time is 
'奖励有效时长  1-1个月；2-2个月；3-3个月 4-6个月 5-两周';

comment on column F_GET_REWARD_RULE.total_limit_amt is 
'奖励累计限额';

comment on column F_GET_REWARD_RULE.product_type is 
'参与活动产品类型';

comment on column F_GET_REWARD_RULE.product_code is 
'参与活动产品代码';

comment on column F_GET_REWARD_RULE.activity_channel is 
'活动渠道   1-金豆荚APP';

comment on column F_GET_REWARD_RULE.trade_amt is 
'交易金额';

comment on column F_GET_REWARD_RULE.back_funds is 
'交易退款总金额';

comment on column F_GET_REWARD_RULE.create_time is 
'创建时间';

comment on column F_GET_REWARD_RULE.update_time is 
'修改时间';

comment on column F_GET_REWARD_RULE.create_by is 
'创建人';

comment on column F_GET_REWARD_RULE.update_by is 
'修改人';
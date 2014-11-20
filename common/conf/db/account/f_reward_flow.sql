/*==============================================================*/
/* Table: F_REWARD_FLOW                                         */
/*==============================================================*/
DROP TABLE IF EXISTS F_REWARD_FLOW;
/*==============================================================*/
/* Table: F_REWARD_FLOW                                         */
/*==============================================================*/
create table F_REWARD_FLOW
(
   ID                   INT8                           not null,
   customer_Id          VARCHAR(30)                    null,
   ACTIVITY_ID          INT8                           null,
   Activity_title       varchar(700)                   null,
   SCENE                varchar(10)                    null,
   STATUS               INT4                           null,
   REWARD_TYPE          VARCHAR(6)                     null,
   OPERATOR_TYPE        INT4                           null,
   REWARD_AMT           INT8                           null,
   MONEY                DECIMAL(18,2)                  null,
   CREATE_TIME          TIMESTAMP                      null,
   constraint PK_F_REWARD_FLOW primary key (ID)
);

comment on column F_REWARD_FLOW.ID is
'主键';

comment on column F_REWARD_FLOW.ACTIVITY_ID is
'活动ID';

comment on column F_REWARD_FLOW.Activity_title is
'活动标题';

comment on column F_REWARD_FLOW.SCENE is
'应用场景';

comment on column F_REWARD_FLOW.STATUS is
'状态 0-未发放，1-已发放，2-发放失败，4-撤回成功，5-撤回失败 6-已兑换';

comment on column F_REWARD_FLOW.REWARD_TYPE is
'奖励类型';

comment on column F_REWARD_FLOW.OPERATOR_TYPE is
'1表示获取  2表示兑换';

comment on column F_REWARD_FLOW.REWARD_AMT is
'奖励数量';

comment on column F_REWARD_FLOW.MONEY is
'奖励折现';

comment on column F_REWARD_FLOW.CREATE_TIME is
'创建时间';


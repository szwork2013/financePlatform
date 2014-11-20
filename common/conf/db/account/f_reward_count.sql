DROP TABLE IF EXISTS F_REWARD_COUNT;
/*==============================================================*/
/* Table: F_REWARD_COUNT                                        */
/*==============================================================*/
/*==============================================================*/
/* Table: F_REWARD_COUNT                                        */
/*==============================================================*/
create table F_REWARD_COUNT
(
   ID                   INT8                           not null,
   customer_Id          VARCHAR(30)                    null,
   REWARD_TYPE          VARCHAR(6)                     null,
   GET_AMOUNT           INT8                           null,
   HOLD_REWARD          INT8                           null,
   FROZEN_REWARD        INT8                           null,
   GET_MONEY            DECIMAL(18,2)                  null,
   HOLD_MONEY           DECIMAL(18,2)                  null,
   FROZEN_MONEY         DECIMAL(18,2)                  null,
   CREATE_TIME          TIMESTAMP                      null,
   UPDATE_TIME          TIMESTAMP                      null,
   constraint PK_F_REWARD_COUNT primary key (ID)
);

comment on column F_REWARD_COUNT.ID is
'主键';

comment on column F_REWARD_COUNT.REWARD_TYPE is
'奖励类型';

comment on column F_REWARD_COUNT.GET_AMOUNT is
'获取奖励数量：获取各种奖励类型的奖励的总和';

comment on column F_REWARD_COUNT.HOLD_REWARD is
'持有奖励数量：现持有各种奖励类型的奖励的总和';

comment on column F_REWARD_COUNT.FROZEN_REWARD is
'冻结奖励数量';

comment on column F_REWARD_COUNT.GET_MONEY is
'获取奖励折现';

comment on column F_REWARD_COUNT.HOLD_MONEY is
'持有奖励折现';

comment on column F_REWARD_COUNT.FROZEN_MONEY is
'冻结奖励折现';

comment on column F_REWARD_COUNT.CREATE_TIME is
'创建时间';

comment on column F_REWARD_COUNT.UPDATE_TIME is
'修改时间';
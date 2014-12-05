DROP TABLE IF EXISTS F_ACTIVITY_STEP;
/*==============================================================*/
/* Table: F_ACTIVITY_STEP                                       */
/*==============================================================*/
create table F_ACTIVITY_STEP
(
   ID                   bigint                         not null,
   OBTAIN_RULE_ID       bigint                         null,
   MIN_VALUE            decimal                        null,
   MAX_VALUE            decimal                        null,
   SHOULD_REWARD        bigint                         null,
   CREATE_TIME          TIMESTAMP                      null,
   UPDATE_TIME          TIMESTAMP                      null,
   constraint PK_F_ACTIVITY_STEP primary key  (ID)
);

comment on column F_ACTIVITY_STEP.ID is
'主键ID';

comment on column F_ACTIVITY_STEP.OBTAIN_RULE_ID is
'获取规则ID';

comment on column F_ACTIVITY_STEP.MIN_VALUE is
'最小值';

comment on column F_ACTIVITY_STEP.MAX_VALUE is
'最大值';

comment on column F_ACTIVITY_STEP.SHOULD_REWARD is
'获取的奖励';

comment on column F_ACTIVITY_STEP.CREATE_TIME is
'创建时间';

comment on column F_ACTIVITY_STEP.UPDATE_TIME is
'修改时间';
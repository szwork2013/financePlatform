DROP TABLE IF EXISTS F_REWARD_TYPE;

/*==============================================================*/
/* Table: F_REWARD_TYPE                                         */
/*==============================================================*/
create table F_REWARD_TYPE
(
   ID                   INT8                           not null,
   CODE                 VARCHAR(6)                     not null,
   NAME                 VARCHAR(16)                    not null,
   UNIT                 INT8                           not null,
   CREATE_TIME          TIMESTAMP                      null,
   UPDATE_TIME          TIMESTAMP                      null,
   CREATE_BY            VARCHAR(30)                    null,
   UPDATE_BY            VARCHAR(30)                    null,
   constraint PK_F_REWARD_TYPE primary key (ID)
);

comment on column F_REWARD_TYPE.ID is
'主键';

comment on column F_REWARD_TYPE.CODE is
'奖励类型编码';

comment on column F_REWARD_TYPE.NAME is
'奖励类型名称';

comment on column F_REWARD_TYPE.UNIT is
'单位';

comment on column F_REWARD_TYPE.CREATE_TIME is
'创建时间';

comment on column F_REWARD_TYPE.UPDATE_TIME is
'更新时间';

comment on column F_REWARD_TYPE.CREATE_BY is
'创建人';

comment on column F_REWARD_TYPE.UPDATE_BY is
'修改人';

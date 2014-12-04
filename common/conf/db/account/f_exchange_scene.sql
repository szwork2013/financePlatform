DROP TABLE IF EXISTS F_EXCAHNGE_SCENE;
/*==============================================================*/
/* Table: F_EXCAHNGE_SCENE                                      */
/*==============================================================*/
create table F_EXCAHNGE_SCENE
(
   ID                   bigint                         not null,
   SCENE                varchar(10)                    null,
   TITLE                varchar(50)                    null,
   STATUS               varchar(1)                     null,
   REWARD_TYPE          varchar(10)                    null,
   EXCHANGE_TYPE        varchar(10)                    null,
   AMOUNT               bigint                         null,
   EXCHANGED_AMT        bigint                         null,
   EXCHANGE_LIMIT       bigint                         null,
   TIME_LIMIT           bigint                         null,
   CREATE_TIME          timestamp                      null,
   UPDATE_TIME          timestamp                      null,
   constraint PK_F_EXCAHNGE_SCENE primary key  (ID),
   constraint AK_SCENE_F_EXCAHN unique (SCENE)
);

comment on column F_EXCAHNGE_SCENE.ID is
'主键ID';

comment on column F_EXCAHNGE_SCENE.SCENE is
'兑换场景编码';

comment on column F_EXCAHNGE_SCENE.TITLE is
'标题';

comment on column F_EXCAHNGE_SCENE.STATUS is
'状态 N表示正常  F表是废弃';

comment on column F_EXCAHNGE_SCENE.REWARD_TYPE is
'奖励类型';

comment on column F_EXCAHNGE_SCENE.EXCHANGE_TYPE is
'兑换类型';

comment on column F_EXCAHNGE_SCENE.AMOUNT is
'所需兑换数量';

comment on column F_EXCAHNGE_SCENE.EXCHANGED_AMT is
'兑换到的数量';

comment on column F_EXCAHNGE_SCENE.EXCHANGE_LIMIT is
'兑换限额';

comment on column F_EXCAHNGE_SCENE.TIME_LIMIT is
'到账期限';

comment on column F_EXCAHNGE_SCENE.CREATE_TIME is
'创建时间';

comment on column F_EXCAHNGE_SCENE.UPDATE_TIME is
'修改时间';
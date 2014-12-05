DROP TABLE IF EXISTS F_ACTIVITY_SCENE;
/*==============================================================*/
/* Table: F_ACTIVITY_SCENE                                      */
/*==============================================================*/
create table F_ACTIVITY_SCENE
(
   ID                   bigint                         not null,
   CODE                 varchar(10)                    not null,
   NAME                 varchar(100)                   null,
   STATUS               varchar(2)                     null,
   ACTIVITY_TYPE        varchar(10)                    null,
   PRD_TYPE             varchar(20)                    null,
   PRD_CODE             varchar(20)                    null,
   CREATE_TIME          timestamp                      null,
   UPDATE_TIME          timestamp                      null,
   constraint PK_F_ACTIVITY_SCENE primary key  (ID),
   constraint AK_KEY_2_F_ACTIVI unique (CODE)
);

comment on column F_ACTIVITY_SCENE.ID is
'主键ID';

comment on column F_ACTIVITY_SCENE.CODE is
'场景编码';

comment on column F_ACTIVITY_SCENE.NAME is
'场景名称';

comment on column F_ACTIVITY_SCENE.STATUS is
'状态  N表示正常  F表示禁止';

comment on column F_ACTIVITY_SCENE.ACTIVITY_TYPE is
'活动类型';

comment on column F_ACTIVITY_SCENE.PRD_TYPE is
'产品类型';

comment on column F_ACTIVITY_SCENE.PRD_CODE is
'产品编码';

comment on column F_ACTIVITY_SCENE.CREATE_TIME is
'创建时间';

comment on column F_ACTIVITY_SCENE.UPDATE_TIME is
'更新时间';
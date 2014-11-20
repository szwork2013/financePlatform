DROP TABLE IF EXISTS F_ACTIVITY;
/*==============================================================*/
/* Table: F_ACTIVITY                                            */
/*==============================================================*/
create table F_ACTIVITY 
(
   ID                   INT8                           not null,
   STATUS               VARCHAR(2)                     not null,
   TITLE                VARCHAR(700)                   not null,
   BEGIN_TIME           DATE                           null,
   END_TIME             DATE                           null,
   SCENE                VARCHAR(10)                    not null,
   APP_ID               VARCHAR(16)                    null,
   IMAGE                VARCHAR(50)                    null,
   URL                  VARCHAR(50)                    null,
   CLICK_TIME           INT8                           null,
   TYPE                 VARCHAR(10)                    null,
   STYLE                VARCHAR(10)                    null,
   CLICK_EVENT          VARCHAR(6)                     null,
   CREATE_TIME          TIMESTAMP                      null,
   UPDATE_TIME          TIMESTAMP                      null,
   CREATE_BY            VARCHAR(30)                    null,
   UPDATE_BY            VARCHAR(30)                    null,
   constraint PK_F_ACTIVITY primary key (ID)
);

comment on column F_ACTIVITY.ID is 
'主键';

comment on column F_ACTIVITY.STATUS is 
'N表示正常   F表示禁用';

comment on column F_ACTIVITY.TITLE is 
'活动标题';

comment on column F_ACTIVITY.BEGIN_TIME is 
'活动开始时间';

comment on column F_ACTIVITY.END_TIME is 
'活动结束时间';

comment on column F_ACTIVITY.SCENE is 
'应用场景';

comment on column F_ACTIVITY.APP_ID is 
'投放产品ID';

comment on column F_ACTIVITY.IMAGE is 
'活动图片';

comment on column F_ACTIVITY.URL is 
'html5名称';

comment on column F_ACTIVITY.CLICK_TIME is 
'点击次数';

comment on column F_ACTIVITY.TYPE is 
'活动类型';

comment on column F_ACTIVITY.STYLE is 
'活动形式';

comment on column F_ACTIVITY.CLICK_EVENT is 
'点击事件';

comment on column F_ACTIVITY.CREATE_TIME is 
'创建时间';

comment on column F_ACTIVITY.UPDATE_TIME is 
'修改时间';

comment on column F_ACTIVITY.CREATE_BY is 
'创建人';

comment on column F_ACTIVITY.UPDATE_BY is 
'修改人';

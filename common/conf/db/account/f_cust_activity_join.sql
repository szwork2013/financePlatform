DROP TABLE IF EXISTS F_CUST_ACTIVITY_JOIN;
/*==============================================================*/
/* Table: F_CUST_ACTIVITY_JOIN                                  */
/*==============================================================*/
create table F_CUST_ACTIVITY_JOIN
(
   ID                   bigint                         not null,
   CUSTOMER_ID          varchar(30)                    null,
   ACTIVITY_ID          bigint                         null,
   SCENE                varchar(10)                    null,
   ISJOINED             int                            null,
   ISCONTINUED          int                            null,
   ISTIME_RELATE        int                            null,
   JOIN_TIME            timestamp                      null,
   SHORT_URL            varchar(200)                   null,
   CREATE_TIME          timestamp                      null,
   UPDATE_TIME          timestamp                      null,
   constraint PK_F_CUST_ACTIVITY_JOIN primary key  (ID)
);

comment on column F_CUST_ACTIVITY_JOIN.ID is
'主键';

comment on column F_CUST_ACTIVITY_JOIN.CUSTOMER_ID is
'客户号';

comment on column F_CUST_ACTIVITY_JOIN.ACTIVITY_ID is
'活动ID';

comment on column F_CUST_ACTIVITY_JOIN.SCENE is
'应用场景';

comment on column F_CUST_ACTIVITY_JOIN.ISJOINED is
'是否已经参加 0表示没有参加 1表示已经参加';

comment on column F_CUST_ACTIVITY_JOIN.ISCONTINUED is
'是否可以继续参加 0表示不可以继续参加 1表示可以继续参加';

comment on column F_CUST_ACTIVITY_JOIN.ISTIME_RELATE is
'是否与时间有关 0表示没有关系  1表示有关系';

comment on column F_CUST_ACTIVITY_JOIN.JOIN_TIME is
'参加时间';

comment on column F_CUST_ACTIVITY_JOIN.SHORT_URL is
'分享短URL';

comment on column F_CUST_ACTIVITY_JOIN.CREATE_TIME is
'创建时间';

comment on column F_CUST_ACTIVITY_JOIN.UPDATE_TIME is
'修改时间';

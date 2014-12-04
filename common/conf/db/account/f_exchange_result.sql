DROP TABLE IF EXISTS F_EXCHANGE_RESULT;
/*==============================================================*/
/* Table: F_EXCHANGE_RESULT                                     */
/*==============================================================*/
create table F_EXCHANGE_RESULT
(
   ID                   bigint                         not null,
   CUSTOMER_ID          varchar(30)                    null,
   EXCHANGE_SCENE       varchar(10)                    null,
   STATUS               int                            null,
   PHONE                varchar(15)                    null,
   BANK_CODE            varchar(20)                    null,
   BANK_CARD_NO         varchar(25)                    null,
   AMOUNT               decimal(18,2)                  null,
   CREATE_TIME          timestamp                      null,
   UPDATE_TIME          timestamp                      null,
   constraint PK_F_EXCHANGE_RESULT primary key  (ID)
);

comment on column F_EXCHANGE_RESULT.ID is
'主键ID';

comment on column F_EXCHANGE_RESULT.CUSTOMER_ID is
'客户号';

comment on column F_EXCHANGE_RESULT.EXCHANGE_SCENE is
'兑换场景编码';

comment on column F_EXCHANGE_RESULT.STATUS is
'状态  0表示未审核 1表示审核通过  2表示审核不通过  3表示兑换成功 4表示兑换失败';

comment on column F_EXCHANGE_RESULT.PHONE is
'手机号';

comment on column F_EXCHANGE_RESULT.BANK_CODE is
'银行编码';

comment on column F_EXCHANGE_RESULT.BANK_CARD_NO is
'银行卡号';

comment on column F_EXCHANGE_RESULT.AMOUNT is
'兑换得到的数量';

comment on column F_EXCHANGE_RESULT.CREATE_TIME is
'创建时间';

comment on column F_EXCHANGE_RESULT.UPDATE_TIME is
'修改时间';
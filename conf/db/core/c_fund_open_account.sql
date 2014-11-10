create table c_fund_open_account
(
   id                   bigint                         null,
   bank_Card_No         varchar(32)                    null,
   bank_code            varchar(20)                    null,
   bank_buyer_name      varchar(10)                    null,
   branch_bank_name     varchar(60)                    null,
   customer_Id          varchar(30)                    null,
   create_time          timestamp                      null,
   update_time          timestamp                      null
)
WITH (
  OIDS=FALSE
);
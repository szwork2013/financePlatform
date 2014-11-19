create table f_shumi_account
(
   id                   bigint                         null,
   customer_id          varchar(30)                    null,
   shumi_tokenKey       varchar(200)                   null,
   shumi_tokenSecret    varchar(200)                   null,
   shumi_userName       varchar(50)                    null,
   shumi_realName       varchar(50)                    null,
   shumi_idNumber       varchar(100)                   null,
   shumi_bankName       varchar(100)                   null,
   shumi_bankCardNo     varchar(100)                   null,
   shumi_bankSerial     varchar(100)                   null,
   shumi_phoneNum       varchar(20)                    null,
   shumi_email          varchar(50)                    null,
   create_time          timestamp                      null,
   update_time          timestamp                      null,
   CONSTRAINT f_shumi_account_pkey PRIMARY KEY (id)
);


alter table c_customer alter column nick_name type character varying(50);

alter table f_shumi_account alter column shumi_tokenKey type character varying(500);
alter table f_shumi_account alter column shumi_tokenSecret type character varying(500);
ALTER TABLE sms_message ADD COLUMN success_ind character varying(1);


ALTER TABLE c_bank_card ADD COLUMN bank_serial character varying(100);
COMMENT ON COLUMN c_bank_card.bank_serial IS '银行序列号';


alter table prd_account_config alter column prd_type_code type character varying(50);
alter table f_fund_agreement alter column fund_company_id type character varying(100);


alter table t_trade alter column trade_no type character varying(100);
alter table t_trade alter column type type character varying(50);
alter table t_trade alter column confirm_status type character varying(50);
alter table t_trade alter column trade_status type character varying(50);
alter table t_trade alter column pay_status type character varying(50);



update dict set code_val='在途' where code_cat= 'FP.TRADE.STATUS' and code_key ='1';
update dict set code_val='成功' where code_cat||code_key ='FP.TRADE.STATUS.2';
update dict set code_val='失败' where code_cat||code_key ='FP.TRADE.STATUS.3';

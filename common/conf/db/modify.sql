alter table c_customer alter column nick_name type character varying(50);
alter table f_shumi_account alter column shumi_tokenKey type character varying(500);
alter table f_shumi_account alter column shumi_tokenSecret type character varying(500);
ALTER TABLE sms_message ADD COLUMN success_ind character varying(1);


ALTER TABLE c_bank_card ADD COLUMN bank_serial character varying(100);
COMMENT ON COLUMN c_bank_card.bank_serial IS '银行序列号';
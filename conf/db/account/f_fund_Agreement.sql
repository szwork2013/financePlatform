CREATE TABLE f_fund_Agreement
(
  id bigint NOT NULL,
  customer_id character varying(30),
  company_code character varying(20),
  create_time timestamp without time zone,
  update_time timestamp without time zone,
  CONSTRAINT c_bank_card_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
CREATE TABLE f_fund_agreement
(
  id bigint NOT NULL,
  customer_id character varying(30),
  company_code character varying(20) NOT NULL,
  create_time timestamp without time zone,
  update_time timestamp without time zone,
  CONSTRAINT pk_f_fund_agreement PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
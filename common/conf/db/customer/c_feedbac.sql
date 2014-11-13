CREATE TABLE c_feedback
(
  id bigint NOT NULL,
  customer_id character varying(30),
  context text,
  mobile character varying(20),
  remark text,
  status character varying(50),
  device_no character varying(50),
  create_time timestamp(6) without time zone,
  update_time timestamp(6) without time zone,
  update_by character varying(30),
  CONSTRAINT c_feedback_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
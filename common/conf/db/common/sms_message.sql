
CREATE TABLE sms_message
(
  id bigint NOT NULL,
  content character varying(200),
  create_time timestamp without time zone,
  mobile character varying(11),
  rec_status character varying(40),
  return_msg character varying(200),
  success_ind character varying(1),
  smsid character varying(40),
  update_time timestamp without time zone,
  CONSTRAINT sms_message_pkey PRIMARY KEY (id)
);
COMMENT ON TABLE sms_message
  IS '短信信息';
CREATE TABLE code_mstr
(
  id bigint NOT NULL,
  code_cat character varying(50), -- 代码类别
  code_abbr character varying(50), -- 业务状态
  code_seq integer, -- 顺序号
  created_by character varying(255),
  created_time timestamp without time zone,
  status character varying(1), -- 有效状态
  magic character varying(255),
  remarks character varying(255),
  parent_code_cat character varying(50), -- 父代码
  CONSTRAINT code_mstr_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON COLUMN code_mstr.code_cat IS '代码类别';
COMMENT ON COLUMN code_mstr.code_abbr IS '业务状态';
COMMENT ON COLUMN code_mstr.code_seq IS '顺序号';
COMMENT ON COLUMN code_mstr.status IS '有效状态';
COMMENT ON COLUMN code_mstr.parent_code_cat IS '父代码';
CREATE TABLE f_subject
(
  id bigint NOT NULL,
  subject_no character varying(6),
  description character varying(20),
  dc_flag character varying(50),
  create_time timestamp without time zone,
  update_time timestamp without time zone,
  delete_time timestamp without time zone,
  CONSTRAINT pk_f_subject PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

comment on table F_SUBJECT is
'科目表';

comment on column F_SUBJECT.ID is
'科目ID';

comment on column F_SUBJECT.SUBJECT_NO is
'科目号';

comment on column F_SUBJECT.DESCRIPTION is
'科目说明';

comment on column F_SUBJECT.DC_FLAG is
'借贷标志  增加资金记贷方 C  较少资金记借方 D';

comment on column F_SUBJECT.CREATE_TIME is
'创建时间';

comment on column F_SUBJECT.UPDATE_TIME is
'修改时间';

comment on column F_SUBJECT.DELETE_TIME is
'删除时间';
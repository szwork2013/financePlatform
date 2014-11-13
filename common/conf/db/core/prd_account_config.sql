CREATE TABLE prd_account_config
(
  id bigint NOT NULL, -- 主键Id
  prd_type_code character varying(8), -- 产品类型编码
  sub_account character varying(10), -- 子账户号
  create_time timestamp without time zone, -- 创建时间
  update_time timestamp without time zone, -- 修改时间
  delete_time timestamp without time zone, -- 删除时间
  CONSTRAINT pk_prd_account_config PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

COMMENT ON TABLE prd_account_config
  IS '产品-账户关系配置表';
COMMENT ON COLUMN prd_account_config.id IS '主键Id';
COMMENT ON COLUMN prd_account_config.prd_type_code IS '产品类型编码';
COMMENT ON COLUMN prd_account_config.sub_account IS '子账户号';
COMMENT ON COLUMN prd_account_config.create_time IS '创建时间';
COMMENT ON COLUMN prd_account_config.update_time IS '修改时间';
COMMENT ON COLUMN prd_account_config.delete_time IS '删除时间';
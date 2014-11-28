create table fund_profit_history
(
   id                   bigint                         not null,
   fund_code             varchar(30)                    null,
   date_time            timestamp                      null,
   percent_seven_days   decimal(16,5)                  null,
   income_per_ten_thousand decimal(16,5)                  null,
   create_time          timestamp                      null,
   update_time          timestamp                      null,
   constraint PK_FUND_PROFIT_HISTORY primary key  (id)
);

COMMENT ON COLUMN fund_profit_history.id IS '主键id';
COMMENT ON COLUMN fund_profit_history.fund_code IS '基金代码';
COMMENT ON COLUMN fund_profit_history.date_time IS '时间';
COMMENT ON COLUMN fund_profit_history.percent_seven_days IS '7日年化';
COMMENT ON COLUMN fund_profit_history.income_per_ten_thousand IS '万份收益';
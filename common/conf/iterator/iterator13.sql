DROP TABLE IF EXISTS c_financial_planner;
CREATE TABLE
    c_financial_planner
    (
        id INTEGER NOT NULL,
        mobile_phone CHARACTER VARYING(15) NOT NULL,
        manager_id INTEGER,
        name CHARACTER VARYING(60) NOT NULL,
        create_time TIMESTAMP,
        update_time TIMESTAMP,
        DELETED              boolean                        null
    );
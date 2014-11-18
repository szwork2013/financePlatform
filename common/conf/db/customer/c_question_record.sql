CREATE TABLE
    c_question_record
    (
        id BIGINT NOT NULL,
        problem TEXT,
        phone_no CHARACTER VARYING(20),
        customer_name CHARACTER VARYING(20),
        remark TEXT,
        status CHARACTER VARYING(50),
        create_time TIMESTAMP(6) WITHOUT TIME ZONE,
        update_time TIMESTAMP(6) WITHOUT TIME ZONE,
        create_by CHARACTER VARYING(10),
        update_by CHARACTER VARYING(10),
        PRIMARY KEY (id)
    )
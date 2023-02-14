--liquibase formatted sql
--changeset george:ddl_10

CREATE TABLE audit
(
    id                      INT                 NOT NULL AUTO_INCREMENT,
    method_name             VARCHAR(60),
    args                    VARCHAR(500),
    return_value            VARCHAR(1000),
    exception_name          VARCHAR(250),
    exception_message       TEXT,
    timestamp               DATETIME,

    CONSTRAINT PK_audit PRIMARY KEY (id)
);
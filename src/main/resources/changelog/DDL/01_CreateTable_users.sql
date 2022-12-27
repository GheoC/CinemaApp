--liquibase formatted sql
--changeset george:ddl_01

CREATE TABLE users
(
    id               INT               NOT NULL AUTO_INCREMENT,
    first_name       VARCHAR(30)       NOT NULL,
    last_name        VARCHAR(30)       NOT NULL,
    email            VARCHAR(50)       NOT NULL,
    phone_number     VARCHAR(10)       NOT NULL,
    password         VARCHAR(80)       NOT NULL,
    role             VARCHAR(8)        NOT NULL,
    status           VARCHAR(8)        NOT NULL,

CONSTRAINT PK_user PRIMARY KEY
    (
        id
    ),
CONSTRAINT AK_user UNIQUE (email)
);
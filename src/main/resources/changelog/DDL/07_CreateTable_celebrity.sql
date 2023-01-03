--liquibase formatted sql
--changeset george:ddl_07

CREATE TABLE celebrity
(
    id               INT                NOT NULL AUTO_INCREMENT,
    name             VARCHAR(150)       NOT NULL,

    CONSTRAINT PK_star PRIMARY KEY
        (id),
    CONSTRAINT AK_celebrity UNIQUE (name)
);
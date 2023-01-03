--liquibase formatted sql
--changeset george:ddl_05

CREATE TABLE genre
(
    id               INT                NOT NULL AUTO_INCREMENT,
    name             VARCHAR(150)       NOT NULL,

    CONSTRAINT PK_genre PRIMARY KEY
        (id)
);
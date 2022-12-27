--liquibase formatted sql
--changeset george:ddl_02

CREATE TABLE movie
(
    id               INT                NOT NULL AUTO_INCREMENT,
    name             VARCHAR(150)       NOT NULL,
    duration         VARCHAR(4)         NOT NULL,
    trailer          VARCHAR(1000)      NOT NULL,
    director         VARCHAR(150)       NOT NULL,
    type             VARCHAR(20)        NOT NULL,
    status           VARCHAR(10)        NOT NULL,

CONSTRAINT PK_movie PRIMARY KEY
    (
        id
    )
);
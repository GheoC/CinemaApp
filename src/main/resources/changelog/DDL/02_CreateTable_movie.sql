--liquibase formatted sql
--changeset george:ddl_02

CREATE TABLE movie
(
    id               INT                NOT NULL AUTO_INCREMENT,
    name             VARCHAR(150)       NOT NULL,
    trailer          VARCHAR(1000)      NOT NULL,
    img              VARCHAR(100)       NOT NULL,
    duration         VARCHAR(10)        NOT NULL,
    is3D             TINYINT            NOT NULL,
    description      VARCHAR(2000)      NOT NULL,
    promoted         TINYINT            NOT NULL,
    imdb             DOUBLE             NOT NULL,
    premier_date     DATE               NOT NULL,
    director         VARCHAR(150)       NOT NULL,
    language         VARCHAR(40)        NOT NULL,
    subtitles        VARCHAR(5)         NOT NULL,
    status           VARCHAR(8)         NOT NULL,

CONSTRAINT PK_movie PRIMARY KEY
    (id),
CONSTRAINT AK_movie UNIQUE (name)
);
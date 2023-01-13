--liquibase formatted sql
--changeset george:ddl_09

CREATE TABLE movie_picture
(
    id               INT                NOT NULL AUTO_INCREMENT,
    name             VARCHAR(150)       NOT NULL,
    picture          LONGBLOB           NOT NULL,

    CONSTRAINT PK_movie_picture PRIMARY KEY (id),
    CONSTRAINT AK_movie_picture UNIQUE (name)
);
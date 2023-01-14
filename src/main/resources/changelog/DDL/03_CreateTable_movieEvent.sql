--liquibase formatted sql
--changeset george:ddl_03

create TABLE movie_event
(
    id                      INT                NOT NULL AUTO_INCREMENT,
    movie_id                INT                NOT NULL,
    room                    VARCHAR(3)         NOT NULL,
    playmovie_datetime      DATETIME           NOT NULL,
    price                   DOUBLE             NOT NULL,
    status                  VARCHAR(8)         NOT NULL DEFAULT 'ACTIVE',

CONSTRAINT PK_movie PRIMARY KEY (id),
CONSTRAINT FK_movie_event_movie FOREIGN KEY (movie_id) REFERENCES movie (id),
CONSTRAINT AK_movie_event UNIQUE (room, playmovie_datetime)
);
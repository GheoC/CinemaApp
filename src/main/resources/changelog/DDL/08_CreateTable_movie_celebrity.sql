--liquibase formatted sql
--changeset george:ddl_08

CREATE TABLE movie_celebrity
(
    movie_id               INT              NOT NULL,
    celebrity_id                INT              NOT NULL,
    CONSTRAINT PK_movie_star PRIMARY KEY
        (movie_id, celebrity_id),
    CONSTRAINT FK_movie_celebrity_movie FOREIGN KEY (movie_id) references movie (id),
    CONSTRAINT FK_movie_celebrity_celebrity FOREIGN KEY (celebrity_id) references celebrity (id)
);
--liquibase formatted sql
--changeset george:ddl_06

CREATE TABLE movie_genre
(
    movie_id               INT              NOT NULL,
    genre_id               INT              NOT NULL,

    CONSTRAINT PK_movie_genre PRIMARY KEY
        (movie_id, genre_id),
    CONSTRAINT FK_movie_genre_movie FOREIGN KEY (movie_id) references movie (id),
    CONSTRAINT FK_movie_genre_genre FOREIGN KEY (genre_id) references genre (id)
);
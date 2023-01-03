--liquibase formatted sql
--changeset george:dml_04

INSERT INTO movie_genre (movie_id, genre_id)
VALUES
    (1,1), (1,2), (1,3),
    (2,1), (2,2), (2,3),
    (3,1), (3,4),
    (4,2), (4,3), (4,5),
    (5,1), (5,2), (5,6),
    (6,1), (6,2), (6,7),
    (7,1), (7,8), (7,6),
    (8,1), (8,9),
    (9,1), (9,2), (9,7),
    (10,1), (10,6);
--liquibase formatted sql
--changeset george:dml_09

INSERT INTO movie_picture (name, picture)
VALUES
    (
        'black-adam.jpg',
    )
--liquibase formatted sql
--changeset george:dml_14

INSERT INTO movie_picture (name, picture)
VALUES
    (
        'the-batman.jpg',
    )
--liquibase formatted sql
--changeset george:dml_13

INSERT INTO movie_picture (name, picture)
VALUES
    (
        'sonic-the-hedgehog-2.jpg',
    )
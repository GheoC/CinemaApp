--liquibase formatted sql
--changeset george:dml_11

INSERT INTO movie_picture (name, picture)
VALUES
    (
        'fantastic-beasts-the-secrets-of-dumbledore.jpg',
    )
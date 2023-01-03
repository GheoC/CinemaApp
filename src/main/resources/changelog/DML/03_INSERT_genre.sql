--liquibase formatted sql
--changeset george:dml_03

INSERT INTO genre (name)
VALUES
    ('ACTION'),
    ('ADVENTURE'),
    ('FANTASY'),
    ('THRILLER'),
    ('FAMILY'),
    ('DRAMA'),
    ('COMEDY'),
    ('CRIME'),
    ('SCIENCE_FICTION'),
    ('HORROR'),
    ('WESTERN'),
    ('ROMANCE');

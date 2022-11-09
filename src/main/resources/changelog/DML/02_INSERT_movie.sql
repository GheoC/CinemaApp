--liquibase formatted sql
--changeset george:dml_02


INSERT INTO movie (name, duration)
VALUES
(
    'The Shawshank Redemption',
    '144'
),
(
    'The Lord of the Rings: The Fellowship of the Ring',
    '178'
),
(
    'The Godfather',
    '175'
)
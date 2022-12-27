--liquibase formatted sql
--changeset george:dml_02


INSERT INTO movie (name, duration, trailer, director, type, status)
VALUES
(
    'The Shawshank Redemption',
    '144',
    'https://www.youtube.com/embed/6hB3S9bIaco',
    'Frank Darabont',
    'DRAMA',
    'PLAYING'
),
(
    'The Lord of the Rings: The Fellowship of the Ring',
    '178',
    'https://www.youtube.com/embed/aStYWD25fAQ',
    'Peter Jackson',
    'FANTASY',
    'PLAYING'
),
(
    'The Godfather',
    '175',
    'https://www.youtube.com/embed/UaVTIH8mujA',
    'Francis Ford Coppola',
    'ACTION',
    'PLAYING'
),
(
    'Pulp Fiction',
    '154',
    'https://www.youtube.com/embed/tGpTpVyI_OQ',
    'Quentin Tarantino',
    'ACTION',
    'PLAYING'
),
(
    'The Dark Knight',
    '152',
    'https://www.youtube.com/embed/EXeTwQWrcwY',
    'Christopher Nolan',
    'ACTION',
    'PLAYING'
)

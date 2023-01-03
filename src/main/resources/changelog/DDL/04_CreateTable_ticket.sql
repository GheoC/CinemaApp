--liquibase formatted sql
--changeset george:ddl_04

create TABLE ticket
(
    id                      INT                NOT NULL AUTO_INCREMENT,
    movie_event_id          INT                NOT NULL,
    user_id                 INT                NOT NULL,
    price_charged           DOUBLE             NOT NULL,
    status                  VARCHAR(8)         NOT NULL DEFAULT 'ORDERED',

    CONSTRAINT PK_ticket PRIMARY KEY
        (id),
    CONSTRAINT FK_ticket_movie_event FOREIGN KEY (movie_event_id) REFERENCES movie_event
        (id),
    CONSTRAINT FK_ticket_user FOREIGN KEY (user_id) REFERENCES users
        (id)
)
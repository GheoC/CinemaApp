--liquibase formatted sql
--changeset george:dml_01

INSERT INTO users (first_name, last_name, email, phone_number, password, role)
VALUES
(
    'will',
    'smith',
    'smith@gmail.com',
    '0771111222',
    '{bcrypt}$2a$10$06Z9Keox4yosNp/3LQW/9e835vdvfRNBTq0H6bXfVY7ObNGaztEQ6',
    'USER'
),
(
    'jackie',
    'chan',
    'chan@gmail.com',
    '0771222333',
    '{bcrypt}$2a$10$2ZXxh5Q.GZdahDmCjPh9yeSUNO0DdGHezLEk/xgNDQjorO01h8Mr6',
    'USER'
),
(
    'carla',
    'raisa',
    'raisa@gmail.com',
    '0770000001',
    '{bcrypt}$2a$10$Agw3bajofDN7s9A6.NBtxeNZlh.g4Lx9QpfZ8UqLk4h//zJmYN8Vy',
    'ADMIN'
);
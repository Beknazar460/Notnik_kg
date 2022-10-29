CREATE TABLE users (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_register TIMESTAMP NOT NULL,
    password VARCHAR(50) NOT NULL,
    phone_number VARCHAR(50) NOT NULL
);
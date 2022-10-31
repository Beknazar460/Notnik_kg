CREATE TABLE users (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    date_of_registration TIMESTAMP NOT NULL,
    password VARCHAR(100) NOT NULL,
    phone_number VARCHAR(50) NOT NULL,
    role VARCHAR(10) NOT NULL
);
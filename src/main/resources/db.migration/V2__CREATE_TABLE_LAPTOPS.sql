CREATE TABLE laptops (
    id BIGSERIAL NOT NULl PRIMARY KEY,
    title VARCHAR(60) NOT NULL  UNIQUE,
    price int NOT NULL ,
    description VARCHAR(250) NOT NULL
);
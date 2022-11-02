CREATE TABLE orders (
    id BIGSERIAL NOT NULl PRIMARY KEY,
    order_date timestamp NOT NULL,
    title_of_product VARCHAR(60) NOT NULL,
    price_of_product VARCHAR(10) NOT NULL,
    user_id BIGINT NOT NULL,
    laptop_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (laptop_id) REFERENCES laptops (id)
);
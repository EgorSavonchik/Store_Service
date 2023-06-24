INSERT INTO stores("name", "address", "delivery_availability", "contact_number", "owner")
VALUES ('first shop', 'street 1', true, '+375 333', 'Egor'),
       ('second shop', 'street 2', false, '+375 333 022', 'Egor'),
       ('third shop', 'street 3', true, '+375 333 022 482', 'Egor');

INSERT INTO products("store_id", "name", "description", "price", "producer")
VALUES (1, 'first product', 'good product', 10, 'Egor'),
       (2, 'second product', 'bad product', 50, 'Egor'),
       (2, 'third product', 'neutral product', 100, 'Egor');

INSERT INTO workers("store_id", "first_name", "last_name", "age", "salary")
VALUES (1, 'Egor', 'Savonchik', 19, 200),
       (3, 'Ivan', 'Ivanov', 20, 100),
       (3, 'Petr', 'Petrov', 15, 300);
CREATE TABLE IF NOT EXISTS stores
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY ,
    delivery_availability boolean NOT NULL ,
    address varchar(255) NOT NULL ,
    contact_number varchar(255) NOT NULL ,
    name varchar(255) NOT NULL ,
    owner varchar(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS products
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    price integer NOT NULL,
    store_id integer REFERENCES stores(id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    description varchar(255),
    name varchar(255),
    producer varchar(255)
    );

CREATE TABLE IF NOT EXISTS workers
(
    id integer PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    store_id integer REFERENCES stores(id)
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    age integer NOT NULL,
    salary integer NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL
    );
CREATE TABLE shopping_cart (
    id UUID PRIMARY KEY,
    customer UUID NOT NULL,
    total_price DECIMAL(19, 2) NOT NULL,
    FOREIGN KEY (customer) REFERENCES customer (id)
);

CREATE TABLE shopping_cart_item (
    id UUID PRIMARY KEY,
    shopping_cart UUID NOT NULL,
    product UUID NOT NULL,
    quantity INTEGER NOT NULL,
    price_at_added_time DECIMAL(19, 2) NOT NULL,
    FOREIGN KEY (shopping_cart) REFERENCES shopping_cart (id),
    FOREIGN KEY (product) REFERENCES product (id)
);

CREATE TABLE address (
    id UUID PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    zip_code VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    additional_info VARCHAR(255),
    customer UUID NOT NULL
);

ALTER TABLE address
ADD CONSTRAINT fk_customer
FOREIGN KEY (customer) REFERENCES customer (id)
ON DELETE CASCADE;

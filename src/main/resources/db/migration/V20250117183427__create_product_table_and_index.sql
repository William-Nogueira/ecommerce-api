CREATE TABLE product (
    id UUID PRIMARY KEY,
    sku VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    label VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    discount DECIMAL(19, 2) NOT NULL,
    installments SMALLINT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE INDEX idx_product_sku ON product (sku);
package dev.williamnogueira.ecommerce.domain.product.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

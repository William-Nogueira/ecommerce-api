package dev.williamnogueira.ecommerce.domain.product.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.ConflictException;

public class DuplicateProductException extends ConflictException {
    public DuplicateProductException(String message) {
        super(message);
    }
}

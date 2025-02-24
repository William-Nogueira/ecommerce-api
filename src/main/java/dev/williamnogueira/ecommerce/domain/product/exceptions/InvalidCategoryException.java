package dev.williamnogueira.ecommerce.domain.product.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.BadRequestException;

public class InvalidCategoryException extends BadRequestException {
    public InvalidCategoryException(String message) {
        super(message);
    }
}

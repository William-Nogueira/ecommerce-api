package dev.williamnogueira.ecommerce.domain.shoppingcart.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.BadRequestException;

public class QuantityGreaterThanAvailableException extends BadRequestException {
    public QuantityGreaterThanAvailableException(String message) {
        super(message);
    }
}

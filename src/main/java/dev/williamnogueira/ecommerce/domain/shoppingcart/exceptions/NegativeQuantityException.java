package dev.williamnogueira.ecommerce.domain.shoppingcart.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.BadRequestException;

public class NegativeQuantityException extends BadRequestException {
    public NegativeQuantityException(String message) {
        super(message);
    }
}

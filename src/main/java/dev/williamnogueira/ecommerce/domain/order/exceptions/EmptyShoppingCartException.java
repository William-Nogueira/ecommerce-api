package dev.williamnogueira.ecommerce.domain.order.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.BadRequestException;

public class EmptyShoppingCartException extends BadRequestException {
    public EmptyShoppingCartException(String message) {
        super(message);
    }
}

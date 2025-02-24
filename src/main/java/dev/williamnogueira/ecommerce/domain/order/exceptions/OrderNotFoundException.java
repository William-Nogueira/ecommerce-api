package dev.williamnogueira.ecommerce.domain.order.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.NotFoundException;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}

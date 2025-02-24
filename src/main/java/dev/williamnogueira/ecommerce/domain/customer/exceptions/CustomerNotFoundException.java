package dev.williamnogueira.ecommerce.domain.customer.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}

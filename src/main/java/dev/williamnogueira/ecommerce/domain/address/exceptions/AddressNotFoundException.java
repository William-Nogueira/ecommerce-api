package dev.williamnogueira.ecommerce.domain.address.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.NotFoundException;

public class AddressNotFoundException extends NotFoundException {
    public AddressNotFoundException(String message) {
        super(message);
    }
}

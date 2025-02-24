package dev.williamnogueira.ecommerce.domain.shoppingcart.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.NotFoundException;

public class ShoppingCartNotFoundException extends NotFoundException {
    public ShoppingCartNotFoundException(String message) {
        super(message);
    }
}

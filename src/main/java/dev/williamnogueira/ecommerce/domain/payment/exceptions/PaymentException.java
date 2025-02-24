package dev.williamnogueira.ecommerce.domain.payment.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.BadRequestException;

public class PaymentException extends BadRequestException {
    public PaymentException(String message) {
        super(message);
    }
}

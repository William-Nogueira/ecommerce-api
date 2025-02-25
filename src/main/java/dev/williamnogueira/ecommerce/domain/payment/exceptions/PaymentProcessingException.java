package dev.williamnogueira.ecommerce.domain.payment.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.BadRequestException;

public class PaymentProcessingException extends BadRequestException {
    public PaymentProcessingException(String message) {
        super(message);
    }
}

package dev.williamnogueira.ecommerce.domain.payment.exceptions;

import dev.williamnogueira.ecommerce.infrastructure.exceptions.BadRequestException;

public class PaymentStatusException extends BadRequestException {
    public PaymentStatusException(String message) {
        super(message);
    }
}

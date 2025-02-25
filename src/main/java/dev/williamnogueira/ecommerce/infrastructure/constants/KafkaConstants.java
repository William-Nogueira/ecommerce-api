package dev.williamnogueira.ecommerce.infrastructure.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaConstants {

    public static final String GROUP_ID = "ecommerce-group";
    public static final String PAYMENT_TOPIC = "payment-topic";
    public static final String PAYMENT_RESPONSE_TOPIC = "payment-response-topic";
    public static final String SENDING_PAYMENT_REQUEST = "Sending payment request for order {}";
    public static final String PAYMENT_REQUEST_IS_NOW_BEING_PROCESSED = "Payment request is now being processed.";
    public static final String RECEIVED_PAYMENT_RESPONSE = "Payment response received for order {}";

}

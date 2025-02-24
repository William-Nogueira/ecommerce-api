package dev.williamnogueira.ecommerce.domain.payment.kafka;

import dev.williamnogueira.ecommerce.domain.payment.dto.PaymentProducerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static dev.williamnogueira.ecommerce.infrastructure.constants.KafkaConstants.PAYMENT_REQUEST_IS_NOW_BEING_PROCESSED;
import static dev.williamnogueira.ecommerce.infrastructure.constants.KafkaConstants.PAYMENT_TOPIC;

@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentProducerResponseDTO sendPaymentRequest(String orderId) {
        kafkaTemplate.send(PAYMENT_TOPIC, orderId);
        return new PaymentProducerResponseDTO(PAYMENT_REQUEST_IS_NOW_BEING_PROCESSED);
    }
}

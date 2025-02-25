package dev.williamnogueira.ecommerce.domain.payment;

import dev.williamnogueira.ecommerce.domain.payment.dto.PaymentRequest;
import dev.williamnogueira.ecommerce.domain.payment.exceptions.PaymentProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.ERROR_PROCESSING_PAYMENT;
import static dev.williamnogueira.ecommerce.infrastructure.constants.KafkaConstants.GROUP_ID;
import static dev.williamnogueira.ecommerce.infrastructure.constants.KafkaConstants.PAYMENT_RESPONSE_TOPIC;
import static dev.williamnogueira.ecommerce.infrastructure.constants.KafkaConstants.PAYMENT_TOPIC;

@Service
public class PaymentService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final WebClient webClient;

    public PaymentService(@Value("${payment.api.url}") String paymentApiUrl, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.webClient = WebClient.create(paymentApiUrl);
    }

    @KafkaListener(topics = PAYMENT_TOPIC, groupId = GROUP_ID)
    public void processPayment(String orderId) {
        sendPaymentRequest(orderId);
    }

    private void sendPaymentRequest(String orderId) {
        var body = new PaymentRequest(orderId);
        webClient.post()
                .uri("/")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> kafkaTemplate.send(PAYMENT_RESPONSE_TOPIC, orderId))
                .doOnError(error -> {
                    throw new PaymentProcessingException(String.format(ERROR_PROCESSING_PAYMENT, orderId, error.getMessage()));
                })
                .subscribe();
    }
}

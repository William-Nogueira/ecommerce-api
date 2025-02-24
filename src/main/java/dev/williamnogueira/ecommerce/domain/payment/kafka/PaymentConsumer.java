package dev.williamnogueira.ecommerce.domain.payment.kafka;

import dev.williamnogueira.ecommerce.domain.order.OrderService;
import dev.williamnogueira.ecommerce.domain.order.OrderStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static dev.williamnogueira.ecommerce.infrastructure.constants.KafkaConstants.GROUP_ID;
import static dev.williamnogueira.ecommerce.infrastructure.constants.KafkaConstants.PAYMENT_RESPONSE_TOPIC;

@Service
@RequiredArgsConstructor
public class PaymentConsumer {

    private final OrderService orderService;

    @KafkaListener(topics = PAYMENT_RESPONSE_TOPIC, groupId = GROUP_ID)
    public void consumePaymentResponse(String orderId) {
        orderService.updateStatus(orderId, OrderStatusEnum.PAID);
    }
}
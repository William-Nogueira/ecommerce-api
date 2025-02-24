package dev.williamnogueira.ecommerce.domain.order;

import dev.williamnogueira.ecommerce.domain.address.AddressTypeEnum;
import dev.williamnogueira.ecommerce.domain.address.exceptions.AddressNotFoundException;
import dev.williamnogueira.ecommerce.domain.order.dto.OrderResponseDTO;
import dev.williamnogueira.ecommerce.domain.order.exceptions.EmptyShoppingCartException;
import dev.williamnogueira.ecommerce.domain.order.exceptions.OrderNotFoundException;
import dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.ADDRESS_NOT_FOUND;
import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.ORDER_NOT_FOUND_WITH_ID;
import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.SHOPPING_CART_IS_EMPTY;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;
    private final OrderMapper orderMapper;

    @Transactional(readOnly = true)
    public OrderResponseDTO findById(UUID id) {
        return orderMapper.toResponseDTO(getEntity(id));
    }

    @Transactional
    public OrderResponseDTO purchaseShoppingCart(UUID customerId) {
        var shoppingCart = shoppingCartService.findByCustomerId(customerId);

        if (shoppingCart.getItems().isEmpty()) {
            throw new EmptyShoppingCartException(SHOPPING_CART_IS_EMPTY);
        }

        var shippingAddress = shoppingCart.getCustomer().getAddress().stream()
                .filter(a -> a.getType().equals(AddressTypeEnum.SHIPPING))
                .findFirst()
                .orElse(shoppingCart.getCustomer().getAddress().stream()
                        .filter(a -> a.getType().equals(AddressTypeEnum.BILLING))
                        .findFirst()
                        .orElseThrow(() -> new AddressNotFoundException(ADDRESS_NOT_FOUND)));

        var orderItems = orderMapper.toOrderItemsEntity(shoppingCart.getItems());
        var orderAddress = orderMapper.toOrderAddressEntity(shippingAddress);

        var order = OrderEntity.builder()
                .customer(shoppingCart.getCustomer())
                .orderItems(orderItems)
                .shippingAddress(orderAddress)
                .totalPrice(shoppingCart.getTotalPrice())
                .status(OrderStatusEnum.PENDING)
                .orderDate(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        order.getOrderItems().forEach(item -> item.setOrder(order));

        orderRepository.save(order);

        shoppingCart.getItems().clear();
        shoppingCart.setTotalPrice(BigDecimal.ZERO);
        shoppingCartService.save(shoppingCart);

        return orderMapper.toResponseDTO(order);
    }

    @Transactional
    public void updateStatus(String id, OrderStatusEnum status) {
        var order = getEntity(UUID.fromString(id));
        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
    }

    public OrderEntity getEntity(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ORDER_NOT_FOUND_WITH_ID, id)));
    }
}

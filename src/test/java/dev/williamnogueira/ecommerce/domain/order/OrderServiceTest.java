package dev.williamnogueira.ecommerce.domain.order;

import dev.williamnogueira.ecommerce.domain.address.AddressTypeEnum;
import dev.williamnogueira.ecommerce.domain.order.exceptions.EmptyShoppingCartException;
import dev.williamnogueira.ecommerce.domain.order.exceptions.OrderNotFoundException;
import dev.williamnogueira.ecommerce.domain.order.dto.OrderResponseDTO;
import dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartEntity;
import dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static dev.williamnogueira.ecommerce.domain.order.OrderTestUtils.*;
import static dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartTestUtils.*;
import static dev.williamnogueira.ecommerce.utils.TestUtils.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ShoppingCartService shoppingCartService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    private OrderEntity orderEntity;
    private OrderResponseDTO orderResponseDTO;
    private ShoppingCartEntity shoppingCartEntity;

    @BeforeEach
    void setUp() {
        orderEntity = createOrderEntity();
        orderResponseDTO = createOrderResponseDTO();
        shoppingCartEntity = createShoppingCartEntity();
    }

    @Test
    void testFindByIdSuccess() {
        // Arrange
        when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toResponseDTO(orderEntity)).thenReturn(orderResponseDTO);

        // Act
        var result = orderService.findById(ID);

        // Assert
        assertThat(result).isNotNull().isEqualTo(orderResponseDTO);
        verify(orderRepository).findById(orderEntity.getId());
        verify(orderMapper).toResponseDTO(orderEntity);
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(OrderNotFoundException.class,
                () -> orderService.findById(ID));
        verify(orderRepository).findById(orderEntity.getId());
    }

    @Test
    void testPurchaseShoppingCartSuccess() {
        // Arrange
        var shippingAddress = shoppingCartEntity.getCustomer().getAddress().get(0);

        when(shoppingCartService.findByCustomerId(ID)).thenReturn(shoppingCartEntity);
        when(orderMapper.toOrderItemsEntity(shoppingCartEntity.getItems())).thenReturn(orderEntity.getOrderItems());
        when(orderMapper.toOrderAddressEntity(shippingAddress)).thenReturn(createOrderAddressEntity());
        when(orderMapper.toResponseDTO(any(OrderEntity.class))).thenReturn(orderResponseDTO);

        // Act
        OrderResponseDTO result = orderService.purchaseShoppingCart(ID);

        // Assert
        assertThat(result).isNotNull().isEqualTo(orderResponseDTO);
        verify(shoppingCartService).findByCustomerId(ID);
        verify(orderRepository).save(any(OrderEntity.class));
        verify(shoppingCartService).save(shoppingCartEntity);
    }

    @Test
    void testPurchaseShoppingCartWithoutShippingAddress() {
        // Arrange
        var shippingAddress = shoppingCartEntity.getCustomer().getAddress().get(0);
        shippingAddress.setType(AddressTypeEnum.BILLING);

        when(shoppingCartService.findByCustomerId(ID)).thenReturn(shoppingCartEntity);
        when(orderMapper.toOrderItemsEntity(shoppingCartEntity.getItems())).thenReturn(orderEntity.getOrderItems());
        when(orderMapper.toOrderAddressEntity(shippingAddress)).thenReturn(createOrderAddressEntity());
        when(orderMapper.toResponseDTO(any(OrderEntity.class))).thenReturn(orderResponseDTO);

        // Act
        OrderResponseDTO result = orderService.purchaseShoppingCart(ID);

        // Assert
        assertThat(result).isNotNull().isEqualTo(orderResponseDTO);
        verify(shoppingCartService).findByCustomerId(ID);
        verify(orderRepository).save(any(OrderEntity.class));
        verify(shoppingCartService).save(shoppingCartEntity);
    }

    @Test
    void testPurchaseShoppingCartEmptyCart() {
        // Arrange
        shoppingCartEntity.getItems().clear();
        when(shoppingCartService.findByCustomerId(shoppingCartEntity.getCustomer().getId()))
                .thenReturn(shoppingCartEntity);

        // Act & Assert
        assertThrows(EmptyShoppingCartException.class,
                () -> orderService.purchaseShoppingCart(ID));
        verify(shoppingCartService).findByCustomerId(shoppingCartEntity.getCustomer().getId());
    }

    @Test
    void testUpdateStatus() {
        // Arrange
        String orderIdStr = orderEntity.getId().toString();
        when(orderRepository.findById(orderEntity.getId())).thenReturn(Optional.of(orderEntity));

        // Act
        orderService.updateStatus(orderIdStr, OrderStatusEnum.PAID);

        // Assert
        assertThat(orderEntity.getStatus()).isEqualTo(OrderStatusEnum.PAID);
        verify(orderRepository).save(orderEntity);
    }
}

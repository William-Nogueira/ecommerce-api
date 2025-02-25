package dev.williamnogueira.ecommerce.domain.shoppingcart;

import dev.williamnogueira.ecommerce.domain.customer.CustomerEntity;
import dev.williamnogueira.ecommerce.domain.customer.CustomerService;
import dev.williamnogueira.ecommerce.domain.product.ProductEntity;
import dev.williamnogueira.ecommerce.domain.product.ProductService;
import dev.williamnogueira.ecommerce.domain.shoppingcart.dto.ShoppingCartRequestDTO;
import dev.williamnogueira.ecommerce.domain.shoppingcart.dto.ShoppingCartResponseDTO;
import dev.williamnogueira.ecommerce.domain.shoppingcart.exceptions.NegativeQuantityException;
import dev.williamnogueira.ecommerce.domain.shoppingcart.exceptions.QuantityGreaterThanAvailableException;
import dev.williamnogueira.ecommerce.domain.shoppingcart.exceptions.ShoppingCartNotFoundException;
import dev.williamnogueira.ecommerce.domain.shoppingcart.shoppingcartitem.ShoppingCartItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static dev.williamnogueira.ecommerce.domain.product.ProductTestUtils.createProductEntity;
import static dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartTestUtils.createShoppingCartEntity;
import static dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartTestUtils.createShoppingCartRequestDTO;
import static dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartTestUtils.createShoppingCartRequestDTOWithInvalidQuantity;
import static dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartTestUtils.createShoppingCartResponseDTO;
import static dev.williamnogueira.ecommerce.utils.TestUtils.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ProductService productService;

    @Mock
    private CustomerService customerService;

    @Mock
    private ShoppingCartItemService shoppingCartItemService;

    @Mock
    private Pageable pageable;

    @Mock
    private ShoppingCartMapper mapper;

    @InjectMocks
    private ShoppingCartService shoppingCartService;

    private ProductEntity productEntity;
    private ShoppingCartEntity shoppingCartEntity;
    private CustomerEntity customerEntity;
    private ShoppingCartRequestDTO shoppingCartRequestDTO;
    private ShoppingCartResponseDTO shoppingCartResponseDTO;

    @BeforeEach
    void setUp() {
        shoppingCartEntity = createShoppingCartEntity();
        customerEntity = createShoppingCartEntity().getCustomer();
        productEntity = createProductEntity();
        shoppingCartRequestDTO = createShoppingCartRequestDTO();
        shoppingCartResponseDTO = createShoppingCartResponseDTO();
    }

    @Test
    void testAddProductToShoppingCart() {
        // arrange
        when(shoppingCartRepository.findByCustomerId(shoppingCartRequestDTO.customerId()))
                .thenReturn(Optional.of(shoppingCartEntity));
        when(customerService.getEntity(shoppingCartRequestDTO.customerId())).thenReturn(customerEntity);
        when(productService.getEntity(productEntity.getId())).thenReturn(productEntity);
        when(mapper.toResponseDTO(shoppingCartEntity)).thenReturn(shoppingCartResponseDTO);

        // act
        var response = shoppingCartService.addToCart(shoppingCartRequestDTO);

        // assert
        assertThat(response).isNotNull().isEqualTo(shoppingCartResponseDTO);
        verify(shoppingCartRepository).findByCustomerId(shoppingCartRequestDTO.customerId());
        verify(customerService).getEntity(shoppingCartRequestDTO.customerId());
        verify(productService).getEntity(productEntity.getId());
    }

    @Test
    void testAddProductToShoppingCartEmptyItems() {
        // arrange
        when(shoppingCartRepository.findByCustomerId(shoppingCartRequestDTO.customerId()))
                .thenReturn(Optional.of(shoppingCartEntity));
        when(customerService.getEntity(shoppingCartRequestDTO.customerId())).thenReturn(customerEntity);
        when(productService.getEntity(productEntity.getId())).thenReturn(productEntity);
        when(mapper.toResponseDTO(shoppingCartEntity)).thenReturn(shoppingCartResponseDTO);

        shoppingCartEntity.getItems().clear();

        // act
        var response = shoppingCartService.addToCart(shoppingCartRequestDTO);

        // assert
        assertThat(response).isNotNull().isEqualTo(shoppingCartResponseDTO);
        verify(shoppingCartRepository).findByCustomerId(shoppingCartRequestDTO.customerId());
        verify(customerService).getEntity(shoppingCartRequestDTO.customerId());
        verify(productService).getEntity(productEntity.getId());
    }

    @Test
    void testAddProductToShoppingCartQuantityGreaterThanAvailable() {
        // arrange
        when(productService.getEntity(productEntity.getId())).thenReturn(productEntity);

        productEntity.setStockQuantity(2);

        // act and assert
        assertThrows(QuantityGreaterThanAvailableException.class,
                () -> shoppingCartService.addToCart(shoppingCartRequestDTO));
    }

    @Test
    void testRemoveProductFromShoppingCart() {
        // arrange
        when(shoppingCartRepository.findByCustomerId(shoppingCartRequestDTO.customerId()))
                .thenReturn(Optional.of(shoppingCartEntity));
        when(customerService.getEntity(shoppingCartRequestDTO.customerId())).thenReturn(customerEntity);
        when(mapper.toResponseDTO(shoppingCartEntity)).thenReturn(shoppingCartResponseDTO);

        // act
        var response = shoppingCartService.removeFromCart(shoppingCartRequestDTO);

        // assert
        assertThat(response).isNotNull().isEqualTo(shoppingCartResponseDTO);
        verify(shoppingCartRepository).findByCustomerId(shoppingCartRequestDTO.customerId());
        verify(customerService).getEntity(shoppingCartRequestDTO.customerId());
        verify(productService).addStockById(productEntity.getId(), shoppingCartRequestDTO.quantity());
    }

    @Test
    void testRemoveFromCartNegativeQuantity() {
        // arrange
        when(shoppingCartRepository.findByCustomerId(shoppingCartRequestDTO.customerId()))
                .thenReturn(Optional.of(shoppingCartEntity));

        var invalidRequest = createShoppingCartRequestDTOWithInvalidQuantity();

        // act and assert
        assertThrows(NegativeQuantityException.class,
                () -> shoppingCartService.removeFromCart(invalidRequest));
    }

    @Test
    void testGetShoppingCartByCustomerId() {
        // arrange
        when(shoppingCartRepository.findByCustomerId(shoppingCartRequestDTO.customerId()))
                .thenReturn(Optional.of(shoppingCartEntity));
        when(mapper.toResponseDTO(shoppingCartEntity)).thenReturn(shoppingCartResponseDTO);

        // act
        var response = shoppingCartService.getShoppingCartByCustomerId(shoppingCartRequestDTO.customerId());

        // assert
        assertThat(response).isNotNull().isEqualTo(shoppingCartResponseDTO);
    }

    @Test
    void testSave() {
        // arrange
        when(shoppingCartRepository.save(shoppingCartEntity)).thenReturn(shoppingCartEntity);

        // act
        shoppingCartService.save(shoppingCartEntity);

        // assert
        verify(shoppingCartRepository).save(shoppingCartEntity);
    }

    @Test
    void testGetEntity() {
        // arrange
        when(shoppingCartRepository.findById(shoppingCartEntity.getId()))
                .thenReturn(Optional.of(shoppingCartEntity));

        // act
        var response = shoppingCartService.getEntity(ID);

        // assert
        assertThat(response).isNotNull().isEqualTo(shoppingCartEntity);
    }

    @Test
    void testGetEntityNotFound() {
        // arrange
        when(shoppingCartRepository.findById(ID)).thenReturn(Optional.empty());

        // act and assert
        assertThrows(ShoppingCartNotFoundException.class, () -> shoppingCartService.getEntity(ID));
    }

}
package dev.williamnogueira.ecommerce.domain.shoppingcart;

import dev.williamnogueira.ecommerce.domain.shoppingcart.dto.ShoppingCartRequestDTO;
import dev.williamnogueira.ecommerce.domain.shoppingcart.dto.ShoppingCartResponseDTO;
import dev.williamnogueira.ecommerce.domain.shoppingcart.shoppingcartitem.ShoppingCartItemEntity;
import dev.williamnogueira.ecommerce.domain.shoppingcart.shoppingcartitem.dto.ShoppingCartItemResponseDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static dev.williamnogueira.ecommerce.domain.customer.CustomerTestUtils.createCustomerEntity;
import static dev.williamnogueira.ecommerce.domain.product.ProductTestUtils.createProductEntity;
import static dev.williamnogueira.ecommerce.utils.TestUtils.ID;

public class ShoppingCartTestUtils {

    public static ShoppingCartEntity createShoppingCartEntity() {
        List<ShoppingCartItemEntity> shoppingCartItemEntities = new ArrayList<>();
        shoppingCartItemEntities.add(createShoppingCartItemEntity());

        return new ShoppingCartEntity(
                ID,
                createCustomerEntity(),
                shoppingCartItemEntities,
                BigDecimal.valueOf(10.99)
        );
    }

    public static ShoppingCartRequestDTO createShoppingCartRequestDTO() {
        return new ShoppingCartRequestDTO(
                ID,
                ID,
                5
        );
    }

    public static ShoppingCartRequestDTO createShoppingCartRequestDTOWithInvalidQuantity() {
        return new ShoppingCartRequestDTO(
                ID,
                ID,
                15
        );
    }

    public static ShoppingCartResponseDTO createShoppingCartResponseDTO() {
        return new ShoppingCartResponseDTO(
                ID,
                ID,
                List.of(createShoppingCartItemResponseDTO()),
                BigDecimal.valueOf(10.99)
        );
    }

    public static ShoppingCartItemEntity createShoppingCartItemEntity() {
        return new ShoppingCartItemEntity(
                ID,
                new ShoppingCartEntity(),
                createProductEntity(),
                5,
                BigDecimal.valueOf(10.99)
        );
    }

    public static ShoppingCartItemResponseDTO createShoppingCartItemResponseDTO() {
        return new ShoppingCartItemResponseDTO(
                ID,
                "Intel Core i9 5500 2.9GHZ",
                1,
                BigDecimal.valueOf(10.99),
                BigDecimal.valueOf(10.99)
        );
    }
}
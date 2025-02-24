package dev.williamnogueira.ecommerce.domain.shoppingcart.shoppingcartitem;

import dev.williamnogueira.ecommerce.domain.product.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.PRODUCT_NOT_FOUND_WITH_ID;

@Service
@RequiredArgsConstructor
public class ShoppingCartItemService {

    private final ShoppingCartItemRepository repository;

    @Transactional
    public void save(ShoppingCartItemEntity item) {
        repository.save(item);
    }

    @Transactional
    public void delete(ShoppingCartItemEntity item) {
        repository.delete(item);
    }

    @Transactional(readOnly = true)
    public ShoppingCartItemEntity findByProductId(UUID productId) {
        return repository.findByProductId(productId)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_NOT_FOUND_WITH_ID));
    }

}

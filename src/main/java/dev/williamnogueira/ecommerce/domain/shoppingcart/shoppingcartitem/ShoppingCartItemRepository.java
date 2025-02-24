package dev.williamnogueira.ecommerce.domain.shoppingcart.shoppingcartitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItemEntity, UUID> {
    Optional<ShoppingCartItemEntity> findByProductId(UUID productId);
}

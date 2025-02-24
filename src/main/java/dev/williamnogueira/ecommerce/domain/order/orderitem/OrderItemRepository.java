package dev.williamnogueira.ecommerce.domain.order.orderitem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface OrderItemRepository extends JpaRepository<OrderItemEntity, UUID> {
}

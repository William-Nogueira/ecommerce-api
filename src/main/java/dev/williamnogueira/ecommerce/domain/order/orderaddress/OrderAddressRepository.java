package dev.williamnogueira.ecommerce.domain.order.orderaddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface OrderAddressRepository extends JpaRepository<OrderAddressEntity, UUID> {
}

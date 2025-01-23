package dev.williamnogueira.ecommerce.domain.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.address WHERE c.active = true")
    Page<CustomerEntity> findAllByActiveTrue(Pageable pageable);

    @Query("SELECT c FROM Customer c LEFT JOIN FETCH c.address WHERE c.id = :id AND c.active = true")
    Optional<CustomerEntity> findByIdAndActiveTrue(UUID id);
}

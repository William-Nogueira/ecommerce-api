package dev.williamnogueira.ecommerce.repository;

import dev.williamnogueira.ecommerce.model.CategoryEnum;
import dev.williamnogueira.ecommerce.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Page<ProductEntity> findAllByActiveTrue(Pageable pageable);

    Optional<ProductEntity> findByIdAndActiveTrue(UUID id);

    Page<ProductEntity> findAllByCategoryIgnoreCaseAndActiveTrue(CategoryEnum category, Pageable pageable);

    Page<ProductEntity> findAllByLabelIgnoreCaseAndActiveTrue(String label, Pageable pageable);

    boolean existsBySku(String sku);
}

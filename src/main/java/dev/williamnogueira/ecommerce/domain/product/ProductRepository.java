package dev.williamnogueira.ecommerce.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    Page<ProductEntity> findAllByActiveTrue(Pageable pageable);

    Optional<ProductEntity> findByIdAndActiveTrue(UUID id);

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.active = true")
    Page<ProductEntity> findAllByCategoryIgnoreCaseAndActiveTrue(ProductCategoryEnum category, Pageable pageable);

    Page<ProductEntity> findAllByLabelIgnoreCaseAndActiveTrue(String label, Pageable pageable);

    boolean existsBySku(String sku);
}

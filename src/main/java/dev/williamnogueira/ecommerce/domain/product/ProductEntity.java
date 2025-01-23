package dev.williamnogueira.ecommerce.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "Product")
@Table(name = "product")
@Getter
@Setter
@RequiredArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(unique = true, nullable = false)
    private String sku;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String label;
    
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ProductCategoryEnum category;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(nullable = false)
    private BigDecimal discount;
    
    @Column(nullable = false)
    private Byte installments;
    
    @Column(nullable = false)
    @ColumnDefault("true")
    private Boolean active;
}

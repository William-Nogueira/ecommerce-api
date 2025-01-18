package dev.williamnogueira.ecommerce.domain.product;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Entity(name = "Product")
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class ProductEntity {
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

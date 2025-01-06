package dev.williamnogueira.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
        private CategoryEnum category;

        @Column(nullable = false)
        private BigDecimal price;

        @Column(nullable = false)
        private BigDecimal discount;

        @Column(nullable = false)
        private Byte installments;

        @Column(nullable = false)
        private Boolean active = true;

        public ProductEntity(String sku, String name, String label, CategoryEnum category, BigDecimal price, BigDecimal discount, Byte installments) {
                this.sku = sku;
                this.name = name;
                this.label = label;
                this.category = category;
                this.price = price;
                this.discount = discount;
                this.installments = installments;
                this.active = true;
        }
}
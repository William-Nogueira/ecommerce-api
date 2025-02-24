package dev.williamnogueira.ecommerce.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank
                                String sku,
                                @NotBlank
                                String name,
                                @NotBlank
                                String label,
                                @NotNull
                                String category,
                                @NotNull
                                BigDecimal price,
                                @NotNull
                                BigDecimal discount,
                                @NotNull
                                Byte installments,
                                @PositiveOrZero
                                Integer stockQuantity,
                                @DefaultValue("true")
                                Boolean active) {
}

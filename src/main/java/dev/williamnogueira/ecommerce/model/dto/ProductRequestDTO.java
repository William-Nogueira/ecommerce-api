package dev.williamnogueira.ecommerce.model.dto;

import dev.williamnogueira.ecommerce.model.CategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;

public record ProductRequestDTO(@NotBlank
                                String sku,
                                @NotBlank
                                String name,
                                @NotBlank
                                String label,
                                @NotNull
                                CategoryEnum category,
                                @NotNull
                                BigDecimal price,
                                @NotNull
                                BigDecimal discount,
                                @NotNull
                                Byte installments,
                                @DefaultValue("true")
                                Boolean active) {
}

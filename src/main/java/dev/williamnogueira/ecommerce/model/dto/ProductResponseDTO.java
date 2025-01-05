package dev.williamnogueira.ecommerce.model.dto;

import dev.williamnogueira.ecommerce.model.CategoryEnum;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponseDTO(UUID id,
                                 String sku,
                                 String name,
                                 String label,
                                 CategoryEnum category,
                                 BigDecimal price,
                                 BigDecimal discount,
                                 Byte installments) {
}

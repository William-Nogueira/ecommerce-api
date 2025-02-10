package dev.williamnogueira.ecommerce.domain.product;

import dev.williamnogueira.ecommerce.domain.product.dto.ProductRequestDTO;
import dev.williamnogueira.ecommerce.domain.product.dto.ProductResponseDTO;

import java.math.BigDecimal;

import static dev.williamnogueira.ecommerce.utils.TestUtils.ID;

public class ProductTestUtils {

    public static ProductEntity createProductEntity() {
        var productEntity = new ProductEntity();
        productEntity.setId(ID);
        productEntity.setSku("INTEL CORE I9 5500");
        productEntity.setName("Intel Core i9 5500 2.9GHZ");
        productEntity.setLabel("Intel");
        productEntity.setCategory(ProductCategoryEnum.CPU);
        productEntity.setPrice(new BigDecimal(1200));
        productEntity.setDiscount(new BigDecimal(10));
        productEntity.setInstallments((byte) 2);
        productEntity.setActive(true);
        return productEntity;
    }

    public static ProductResponseDTO createProductResponseDTO() {
        return new ProductResponseDTO(ID,
                "INTEL CORE I9 5500",
                "Intel Core i9 5500 2.9GHZ",
                "Intel",
                "CPU",
                new BigDecimal(1200),
                new BigDecimal(10),
                (byte) 2);
    }

    public static ProductRequestDTO createProductRequestDTO() {
        return new ProductRequestDTO("INTEL CORE I9 5500",
                "Intel Core i9 5500 2.9GHZ",
                "Intel",
                "CPU",
                new BigDecimal(1200),
                new BigDecimal(10),
                (byte) 2,
                true);
    }

    public static ProductRequestDTO createProductWithNewSku() {
        return new ProductRequestDTO("INTEL CORE I9 5500 NEW",
                "Intel Core i9 5500 2.9GHZ",
                "Intel",
                "CPU",
                new BigDecimal(1200),
                new BigDecimal(10),
                (byte) 2,
                true);
    }

    public static ProductRequestDTO createInvalidProductRequestDTO() {
        return new ProductRequestDTO("INTEL CORE I9 5500",
                "Intel Core i9 5500 2.9GHZ",
                "Intel",
                "INVALID CATEGORY",
                new BigDecimal(1200),
                new BigDecimal(10),
                (byte) 2,
                true);
    }
}

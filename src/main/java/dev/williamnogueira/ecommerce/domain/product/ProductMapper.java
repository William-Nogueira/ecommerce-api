package dev.williamnogueira.ecommerce.domain.product;

import dev.williamnogueira.ecommerce.domain.product.dto.ProductRequestDTO;
import dev.williamnogueira.ecommerce.domain.product.dto.ProductResponseDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", defaultValue = "true")
    ProductEntity toEntity(ProductRequestDTO dto);

    ProductResponseDTO toResponseDTO(ProductEntity entity);
}

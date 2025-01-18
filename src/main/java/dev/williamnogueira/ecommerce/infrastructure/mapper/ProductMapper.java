package dev.williamnogueira.ecommerce.infrastructure.mapper;

import dev.williamnogueira.ecommerce.model.ProductEntity;
import dev.williamnogueira.ecommerce.model.dto.ProductRequestDTO;
import dev.williamnogueira.ecommerce.model.dto.ProductResponseDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", defaultValue = "true")
    ProductEntity toEntity(ProductRequestDTO dto);

    ProductResponseDTO toResponseDTO(ProductEntity entity);
}

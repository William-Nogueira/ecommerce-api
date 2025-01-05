package dev.williamnogueira.ecommerce.service;

import dev.williamnogueira.ecommerce.infrastructure.mapper.ProductMapper;
import dev.williamnogueira.ecommerce.infrastructure.exceptions.DuplicateProductException;
import dev.williamnogueira.ecommerce.infrastructure.exceptions.ProductNotFoundException;
import dev.williamnogueira.ecommerce.model.CategoryEnum;
import dev.williamnogueira.ecommerce.model.ProductEntity;
import dev.williamnogueira.ecommerce.model.dto.ProductRequestDTO;
import dev.williamnogueira.ecommerce.model.dto.ProductResponseDTO;
import dev.williamnogueira.ecommerce.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.PRODUCT_NOT_FOUND_WITH_ID;
import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.SKU_ALREADY_EXISTS;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAll(Pageable pageable) {
        return productRepository.findAllByActiveTrue(pageable).map(productMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public ProductResponseDTO findById(UUID id) {
        return productRepository.findByIdAndActiveTrue(id)
                .map(productMapper::toResponseDTO)
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_WITH_ID, id)));
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAllByCategory(CategoryEnum category, Pageable pageable) {
        return productRepository.findAllByCategoryIgnoreCaseAndActiveTrue(category, pageable)
                .map(productMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public Page<ProductResponseDTO> findAllByLabel(String label, Pageable pageable) {
        return productRepository.findAllByLabelIgnoreCaseAndActiveTrue(label, pageable)
                .map(productMapper::toResponseDTO);
    }

    @Transactional
    public ProductResponseDTO create(ProductRequestDTO product) {

        if (productRepository.existsBySku(product.sku())) {
            throw new DuplicateProductException(SKU_ALREADY_EXISTS);
        }

        return productMapper.toResponseDTO(productRepository.save(productMapper.toEntity(product)));
    }

    @Transactional
    public ProductResponseDTO updateById(UUID id, ProductRequestDTO product) {

        var entity = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_WITH_ID, id)));

        if (!Objects.equals(entity.getSku(), product.sku()) && productRepository.existsBySku(product.sku())) {
            throw new DuplicateProductException(SKU_ALREADY_EXISTS);
        }

        updateEntityFields(entity, product);

        return productMapper.toResponseDTO(productRepository.save(entity));
    }

    @Transactional
    public void deleteById(UUID id) {
        var product = productRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new ProductNotFoundException(String.format(PRODUCT_NOT_FOUND_WITH_ID, id)));

        product.setActive(false);
        productRepository.save(product);
    }

    private void updateEntityFields(ProductEntity entity, ProductRequestDTO product) {
        entity.setSku(product.sku());
        entity.setName(product.name());
        entity.setLabel(product.label());
        entity.setCategory(product.category());
        entity.setPrice(product.price());
        entity.setDiscount(product.discount());
        entity.setInstallments(product.installments());
    }

}

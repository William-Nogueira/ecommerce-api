package dev.williamnogueira.ecommerce.controller;

import dev.williamnogueira.ecommerce.model.CategoryEnum;
import dev.williamnogueira.ecommerce.model.dto.ProductRequestDTO;
import dev.williamnogueira.ecommerce.model.dto.ProductResponseDTO;
import dev.williamnogueira.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDTO>> findAll(Pageable pageable) {
        var response = productService.findAll(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Page<ProductResponseDTO>> findByCategory(@PathVariable("category") CategoryEnum category, Pageable pageable) {
        return ResponseEntity.ok(productService.findAllByCategory(category, pageable));
    }

    @GetMapping("/label/{label}")
    public ResponseEntity<Page<ProductResponseDTO>> findByLabel(@PathVariable("label") String label, Pageable pageable) {
        return ResponseEntity.ok(productService.findAllByLabel(label, pageable));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> create(@Valid @RequestBody ProductRequestDTO product) {
        var createdProduct = productService.create(product);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdProduct.id())
                .toUri();
        return ResponseEntity.created(location).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateById(@PathVariable UUID id, @Valid @RequestBody ProductRequestDTO product) {
        var updatedProduct = productService.updateById(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

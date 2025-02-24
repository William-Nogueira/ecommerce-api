package dev.williamnogueira.ecommerce.controller;

import dev.williamnogueira.ecommerce.domain.shoppingcart.ShoppingCartService;
import dev.williamnogueira.ecommerce.domain.shoppingcart.dto.ShoppingCartRequestDTO;
import dev.williamnogueira.ecommerce.domain.shoppingcart.dto.ShoppingCartResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<ShoppingCartResponseDTO> addToCart(@Valid @RequestBody ShoppingCartRequestDTO request) {
        return ResponseEntity.ok(shoppingCartService.addToCart(request));
    }

    @DeleteMapping("/remove")
    public ResponseEntity<ShoppingCartResponseDTO> removeFromCart(@Valid @RequestBody ShoppingCartRequestDTO request) {
        return ResponseEntity.ok(shoppingCartService.removeFromCart(request));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<ShoppingCartResponseDTO> getShoppingCartByCustomerId(@PathVariable UUID customerId) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartByCustomerId(customerId));
    }
}

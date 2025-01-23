package dev.williamnogueira.ecommerce.domain.customer.dto;

import dev.williamnogueira.ecommerce.domain.address.dto.AddressResponseDTO;

import java.util.List;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,
        String name,
        String email,
        String phoneNumber,
        List<AddressResponseDTO> address
) {
}

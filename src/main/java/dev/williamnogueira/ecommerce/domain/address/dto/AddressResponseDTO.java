package dev.williamnogueira.ecommerce.domain.address.dto;

import java.util.UUID;

public record AddressResponseDTO(
    UUID id,
    String street,
    String number,
    String neighborhood,
    String city,
    String state,
    String country,
    String zipCode,
    String type,
    String additionalInfo
) {
}

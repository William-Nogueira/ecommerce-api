package dev.williamnogueira.ecommerce.domain.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddressRequestDTO (
    @NotBlank
    String street,
    @NotBlank
    String number,
    @NotBlank
    String neighborhood,
    @NotBlank
    String city,
    @NotBlank
    String state,
    @NotBlank
    String country,
    @NotBlank
    String zipCode,
    @NotBlank
    String type,
    @NotNull
    UUID customer,
    String additionalInfo
){
}

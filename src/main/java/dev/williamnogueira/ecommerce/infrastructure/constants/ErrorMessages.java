package dev.williamnogueira.ecommerce.infrastructure.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {

    public static final String PRODUCT_NOT_FOUND_WITH_ID = "Product with id %s not found.";
    public static final String SKU_ALREADY_EXISTS = "A product with the given SKU already exists.";
    public static final String INVALID_CATEGORY = "Invalid category: %s";
    public static final String REQUEST_BODY_MISSING = "Request body is missing or malformed.";
    public static final String VALIDATION_FAILED = "Validation failed for one or more fields";
    public static final String CUSTOMER_NOT_FOUND_WITH_ID = "Customer with id %s not found.";
    public static final String ADDRESS_NOT_FOUND_WITH_ID = "Address with id %s not found.";

}

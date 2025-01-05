package dev.williamnogueira.ecommerce.infrastructure.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {

    public static final String PRODUCT_NOT_FOUND_WITH_ID = "Product with id %s not found.";
    public static final String SKU_ALREADY_EXISTS = "A product with the given SKU already exists.";
    public static final String REQUEST_BODY_MISSING_ERROR = "Request body is missing or malformed.";
    public static final String VALIDATION_FAILED_ERROR = "Validation failed for one or more fields";

}

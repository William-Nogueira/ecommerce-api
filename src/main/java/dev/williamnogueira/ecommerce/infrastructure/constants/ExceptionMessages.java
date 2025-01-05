package dev.williamnogueira.ecommerce.infrastructure.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionMessages {

    public static final String DUPLICATE_EXCEPTION = "Duplicate product exception: {}";
    public static final String PRODUCT_NOT_FOUND_EXCEPTION = "Product not found exception: {}";
    public static final String GENERIC_EXCEPTION = "Unexpected error occurred: {}";
    public static final String REQUEST_BODY_MISSING_EXCEPTION = "Request body is missing or malformed: {}";
    public static final String VALIDATION_FAILED_EXCEPTION = "Validation failed for one or more fields: {}";

}

package dev.williamnogueira.ecommerce.domain.customer;

import dev.williamnogueira.ecommerce.domain.customer.dto.CustomerRequestDTO;
import dev.williamnogueira.ecommerce.domain.customer.dto.CustomerResponseDTO;

import java.util.List;

import static dev.williamnogueira.ecommerce.domain.address.AddressTestUtils.createAddressEntity;
import static dev.williamnogueira.ecommerce.domain.address.AddressTestUtils.createAddressRequestDTO;
import static dev.williamnogueira.ecommerce.domain.address.AddressTestUtils.createAddressResponseDTO;
import static dev.williamnogueira.ecommerce.utils.TestUtils.ID;

public class CustomerTestUtils {

    public static CustomerEntity createCustomerEntity() {
        var customerEntity = new CustomerEntity();
        customerEntity.setId(ID);
        customerEntity.setName("John Doe");
        customerEntity.setEmail("2xO1t@example.com");
        customerEntity.setPhoneNumber("1234567890");
        customerEntity.setAddress(List.of(createAddressEntity()));
        customerEntity.setActive(true);
        return customerEntity;
    }

    public static CustomerRequestDTO createCustomerRequestDTO() {
        return new CustomerRequestDTO(
                "John Doe",
                "2xO1t@example.com",
                "1234567890",
                List.of(createAddressRequestDTO()),
                true);
    }

    public static CustomerResponseDTO createCustomerResponseDTO() {
        return new CustomerResponseDTO(ID,
                "John Doe",
                "2xO1t@example.com",
                "1234567890",
                List.of(createAddressResponseDTO()));
    }

}

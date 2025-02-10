package dev.williamnogueira.ecommerce.domain.address;

import dev.williamnogueira.ecommerce.domain.address.dto.AddressRequestDTO;
import dev.williamnogueira.ecommerce.domain.address.dto.AddressResponseDTO;

import static dev.williamnogueira.ecommerce.utils.TestUtils.ID;

public class AddressTestUtils {

    public static AddressEntity createAddressEntity() {
        var addressEntity = new AddressEntity();
        addressEntity.setId(ID);
        addressEntity.setStreet("123 Main St");
        addressEntity.setNumber("123");
        addressEntity.setNeighborhood("Downtown");
        addressEntity.setCity("New York");
        addressEntity.setState("NY");
        addressEntity.setCountry("USA");
        addressEntity.setZipCode("10001");
        addressEntity.setType(AddressTypeEnum.SHIPPING);
        addressEntity.setAdditionalInfo("Home address");
        return addressEntity;
    }

    public static AddressResponseDTO createAddressResponseDTO() {
        return new AddressResponseDTO(ID,
                "123 Main St",
                "123",
                "Downtown",
                "New York",
                "NY",
                "USA",
                "10001",
                "SHIPPING",
                "Home address");
    }

    public static AddressRequestDTO createAddressRequestDTO() {
        return new AddressRequestDTO(
                "123 Main St",
                "123",
                "Downtown",
                "New York",
                "NY",
                "USA",
                "10001",
                "SHIPPING",
                ID,
                "Home address");
    }

}

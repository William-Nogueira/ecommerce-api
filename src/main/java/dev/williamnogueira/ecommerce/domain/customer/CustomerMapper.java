package dev.williamnogueira.ecommerce.domain.customer;

import dev.williamnogueira.ecommerce.domain.address.AddressMapper;
import dev.williamnogueira.ecommerce.domain.customer.dto.CustomerRequestDTO;
import dev.williamnogueira.ecommerce.domain.customer.dto.CustomerResponseDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = AddressMapper.class)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", defaultValue = "true")
    CustomerEntity toEntity(CustomerRequestDTO customerRequestDTO);

    CustomerResponseDTO toResponseDTO(CustomerEntity customerEntity);

    default CustomerEntity toEntityWithAddresses(CustomerRequestDTO customerRequestDTO) {
        CustomerEntity customerEntity = toEntity(customerRequestDTO);

        if (customerEntity.getAddress() != null) {
            customerEntity.getAddress().forEach(address -> address.setCustomer(customerEntity));
        }

        return customerEntity;
    }

}

package dev.williamnogueira.ecommerce.domain.address;

import dev.williamnogueira.ecommerce.domain.address.dto.AddressRequestDTO;
import dev.williamnogueira.ecommerce.domain.address.dto.AddressResponseDTO;
import dev.williamnogueira.ecommerce.domain.address.exceptions.AddressNotFoundException;
import dev.williamnogueira.ecommerce.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.ADDRESS_NOT_FOUND_WITH_ID;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final CustomerService customerService;

    @Transactional
    public AddressResponseDTO create(AddressRequestDTO address) {
        var customerEntity = customerService.getEntity(address.customer());

        var addressEntity = addressMapper.toEntity(address);
        addressEntity.setCustomer(customerEntity);

        return addressMapper.toResponseDTO(addressRepository.save(addressEntity));
    }

    @Transactional
    public AddressResponseDTO updateById(UUID id, AddressRequestDTO address) {
        var entity = getEntity(id);

        updateEntityFields(entity, address);

        return addressMapper.toResponseDTO(addressRepository.save(entity));
    }

    private void updateEntityFields(AddressEntity entity, AddressRequestDTO address) {
        entity.setCustomer(customerService.getEntity(address.customer()));
        entity.setStreet(address.street());
        entity.setNumber(address.number());
        entity.setNeighborhood(address.neighborhood());
        entity.setCity(address.city());
        entity.setState(address.state());
        entity.setCountry(address.country());
        entity.setZipCode(address.zipCode());
        entity.setType(AddressTypeEnum.valueOf(address.type()));
        entity.setAdditionalInfo(address.additionalInfo());
    }

    public AddressEntity getEntity(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(String.format(ADDRESS_NOT_FOUND_WITH_ID, id)));
    }

}

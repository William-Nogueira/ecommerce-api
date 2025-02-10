package dev.williamnogueira.ecommerce.domain.customer;

import dev.williamnogueira.ecommerce.domain.address.AddressMapper;
import dev.williamnogueira.ecommerce.domain.customer.dto.CustomerRequestDTO;
import dev.williamnogueira.ecommerce.domain.customer.dto.CustomerResponseDTO;
import dev.williamnogueira.ecommerce.domain.customer.exceptions.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.CUSTOMER_NOT_FOUND_WITH_ID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AddressMapper addressMapper;

    @Transactional(readOnly = true)
    public Page<CustomerResponseDTO> findAll(Pageable pageable) {
        return customerRepository.findAllByActiveTrue(pageable).map(customerMapper::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public CustomerResponseDTO findById(UUID id) {
        var entity = getEntity(id);
        return customerMapper.toResponseDTO(entity);
    }

    @Transactional
    public CustomerResponseDTO create(CustomerRequestDTO customer) {
        var entity = customerMapper.toEntityWithAddresses(customer);
        return customerMapper.toResponseDTO(customerRepository.save(entity));
    }

    @Transactional
    public CustomerResponseDTO updateById(UUID id, @Valid CustomerRequestDTO customer) {
        var entity = getEntity(id);

        updateEntityFields(entity, customer);

        return customerMapper.toResponseDTO(customerRepository.save(entity));
    }

    public CustomerEntity getEntity(UUID id) {
        return customerRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format(CUSTOMER_NOT_FOUND_WITH_ID, id)));
    }

    private void updateEntityFields(CustomerEntity entity, CustomerRequestDTO customer) {
        entity.setName(customer.name());
        entity.setEmail(customer.email());
        entity.setPhoneNumber(customer.phoneNumber());
        entity.setAddress(customer.address().stream().map(addressMapper::toEntity).toList());
    }
}

package dev.williamnogueira.ecommerce.domain.customer;

import dev.williamnogueira.ecommerce.domain.address.AddressMapper;
import dev.williamnogueira.ecommerce.domain.customer.dto.CustomerRequestDTO;
import dev.williamnogueira.ecommerce.domain.customer.dto.CustomerResponseDTO;
import dev.williamnogueira.ecommerce.domain.customer.exceptions.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static dev.williamnogueira.ecommerce.domain.customer.CustomerTestUtils.createCustomerEntity;
import static dev.williamnogueira.ecommerce.domain.customer.CustomerTestUtils.createCustomerRequestDTO;
import static dev.williamnogueira.ecommerce.domain.customer.CustomerTestUtils.createCustomerResponseDTO;
import static dev.williamnogueira.ecommerce.infrastructure.constants.ErrorMessages.CUSTOMER_NOT_FOUND_WITH_ID;
import static dev.williamnogueira.ecommerce.utils.TestUtils.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private AddressMapper addressMapper;

    @Mock
    private Pageable pageable;

    @InjectMocks
    private CustomerService customerService;

    static CustomerEntity customerEntity;
    static CustomerResponseDTO customerResponseDTO;
    static CustomerRequestDTO customerRequestDTO;

    @BeforeAll
    static void setUp() {
        customerEntity = createCustomerEntity();
        customerRequestDTO = createCustomerRequestDTO();
        customerResponseDTO = createCustomerResponseDTO();
    }

    @Test
    void testFindAll() {
        // arrange
        Page<CustomerEntity> entityPage = new PageImpl<>(List.of(customerEntity));

        when(customerRepository.findAllByActiveTrue(pageable)).thenReturn(entityPage);
        when(customerMapper.toResponseDTO(customerEntity)).thenReturn(customerResponseDTO);

        // act
        Page<CustomerResponseDTO> response = customerService.findAll(pageable);

        // assert
        assertThat(response).isNotNull();
        assertThat(response.getContent()).hasSize(1).containsExactly(customerResponseDTO);
        verify(customerRepository).findAllByActiveTrue(pageable);
        verify(customerMapper).toResponseDTO(customerEntity);
    }

    @Test
    void testFindById() {
        // arrange
        when(customerRepository.findByIdAndActiveTrue(ID)).thenReturn(Optional.of(customerEntity));
        when(customerMapper.toResponseDTO(customerEntity)).thenReturn(customerResponseDTO);

        // act
        var response = customerService.findById(ID);

        // assert
        assertThat(response).isNotNull().isEqualTo(customerResponseDTO);
        verify(customerRepository).findByIdAndActiveTrue(ID);
        verify(customerMapper).toResponseDTO(customerEntity);
    }

    @Test
    void testCreate() {
        // arrange
        when(customerMapper.toEntityWithAddresses(customerRequestDTO)).thenReturn(customerEntity);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        when(customerMapper.toResponseDTO(customerEntity)).thenReturn(customerResponseDTO);

        // act
        var response = customerService.create(customerRequestDTO);

        // assert
        assertThat(response).isNotNull().isEqualTo(customerResponseDTO);
        verify(customerRepository).save(customerEntity);
        verify(customerMapper).toEntityWithAddresses(customerRequestDTO);
        verify(customerMapper).toResponseDTO(customerEntity);

    }

    @Test
    void testUpdateById() {
        // arrange
        when(customerRepository.findByIdAndActiveTrue(ID)).thenReturn(Optional.of(customerEntity));
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        when(customerMapper.toResponseDTO(customerEntity)).thenReturn(customerResponseDTO);

        // act
        var response = customerService.updateById(ID, customerRequestDTO);

        // assert
        assertThat(response).isNotNull().isEqualTo(customerResponseDTO);
        verify(customerRepository).save(customerEntity);
        verify(customerMapper).toResponseDTO(customerEntity);
    }

    @Test
    void testGetEntity() {
        // arrange
        when(customerRepository.findByIdAndActiveTrue(ID)).thenReturn(Optional.of(customerEntity));

        // act
        var response = customerService.getEntity(ID);

        // assert
        assertThat(response).isNotNull().isEqualTo(customerEntity);
        verify(customerRepository).findByIdAndActiveTrue(ID);
    }

    @Test
    void testGetEntityDoesNotFindEntity() {
        // arrange
        when(customerRepository.findByIdAndActiveTrue(ID)).thenReturn(Optional.empty());

        // act & assert
        var exception = assertThrows(CustomerNotFoundException.class, () -> customerService.getEntity(ID));
        assertThat(exception.getMessage()).contains(String.format(CUSTOMER_NOT_FOUND_WITH_ID, ID));
        verify(customerRepository).findByIdAndActiveTrue(ID);
    }
}

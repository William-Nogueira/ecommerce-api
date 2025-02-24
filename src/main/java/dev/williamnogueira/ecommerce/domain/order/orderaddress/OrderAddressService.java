package dev.williamnogueira.ecommerce.domain.order.orderaddress;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAddressService {

    private final OrderAddressRepository orderAddressRepository;

}

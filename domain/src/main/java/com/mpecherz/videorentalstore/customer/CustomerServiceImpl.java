package com.mpecherz.videorentalstore.customer;

import com.mpecherz.videorentalstore.model.CustomerService;
import com.mpecherz.videorentalstore.model.Customer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
@AllArgsConstructor
class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(customerRepository.getAll()));
    }

    @Override
    public Optional<Customer> get(String id) {
        return Optional.ofNullable(customerRepository.get(id));
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.update(customer);
    }
}

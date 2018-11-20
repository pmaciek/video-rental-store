package com.mpecherz.videorentalstore.customer;

import com.mpecherz.videorentalstore.model.Customer;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;

@AllArgsConstructor
class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<String, Customer> customerRepository;

    @Override
    public Collection<Customer> getAll() {
        return customerRepository.values();
    }

    @Override
    public Customer get(String id) {
        return customerRepository.get(id);
    }

    @Override
    public Customer update(Customer customer) {
        customerRepository.put(customer.getId(), customer);
        return customer;
    }
}

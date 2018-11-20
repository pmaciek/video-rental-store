package com.mpecherz.videorentalstore.model;


import com.mpecherz.videorentalstore.model.Customer;

import java.util.List;
import java.util.Optional;


public interface CustomerService {

    List<Customer> getAll();

    Optional<Customer> get(String id);

    Customer updateCustomer(Customer customer);
}

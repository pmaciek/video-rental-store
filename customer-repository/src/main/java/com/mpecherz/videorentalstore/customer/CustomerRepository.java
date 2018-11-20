package com.mpecherz.videorentalstore.customer;

import com.mpecherz.videorentalstore.model.Customer;

import java.util.Collection;


interface CustomerRepository {
    Collection<Customer> getAll();

    Customer get(String id);

    Customer update(Customer customer);
}

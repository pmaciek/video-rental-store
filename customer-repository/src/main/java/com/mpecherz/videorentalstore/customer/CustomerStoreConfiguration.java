package com.mpecherz.videorentalstore.customer;

import com.mpecherz.videorentalstore.model.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@Configuration
class CustomerStoreConfiguration {

    @Bean
    InMemoryCustomerRepository inMemoryCustomerRepository() {

        Map<String, Customer> customers = new ConcurrentHashMap<String, Customer>() {
            {
                for (int i = 0; i < 10; i++) {
                    createCustomer();
                }
            }

            private void createCustomer() {
                String uuid = UUID.randomUUID().toString();
                put(uuid, new Customer(uuid, 0));
            }
        };
        return new InMemoryCustomerRepository(customers);
    }

}

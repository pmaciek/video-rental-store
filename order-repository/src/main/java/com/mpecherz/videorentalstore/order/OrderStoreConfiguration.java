package com.mpecherz.videorentalstore.order;

import com.mpecherz.videorentalstore.model.Order;
import com.mpecherz.videorentalstore.model.Rental;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;


@Configuration
class OrderStoreConfiguration {

    @Bean
    InMemoryOrderRepository inMemoryOrderRepository() {
        return new InMemoryOrderRepository(new ConcurrentHashMap<>(), new ConcurrentHashMap<>());
    }

}

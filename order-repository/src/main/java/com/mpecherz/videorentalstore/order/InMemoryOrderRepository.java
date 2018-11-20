package com.mpecherz.videorentalstore.order;

import com.mpecherz.videorentalstore.model.Order;
import com.mpecherz.videorentalstore.model.Rental;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> orderRepository;
    private final Map<String, Rental> rentalRepository;

    @Override
    public Collection<Order> getAllOrders() {
        return orderRepository.values();
    }

    @Override
    public Rental getRental(String id) {
        return rentalRepository.get(id);
    }

    @Override
    public Rental updateRental(Rental rental) {
        rentalRepository.put(rental.getId(), rental);
        return rental;
    }

    @Override
    public Order getOrder(String id) {
        return orderRepository.get(id);
    }

    @Override
    public Order saveOrder(Order order) {
        orderRepository.put(order.getId(), order);
        return order;
    }

    @Override
    public Order updateOrder(Order order) {
        orderRepository.put(order.getId(), order);
        return order;
    }

    @Override
    public Rental saveRental(Rental rental) {
        rentalRepository.put(rental.getId(), rental);
        return rental;
    }

    @Override
    public Collection getRentalsByOrderId(String id) {
        Collection<String> rentals = orderRepository.get(id).getRentals();
        return rentals.stream().map(rentalRepository::get).collect(Collectors.toList());
    }
}

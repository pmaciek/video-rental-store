package com.mpecherz.videorentalstore.order;

import com.mpecherz.videorentalstore.model.OrderService;
import com.mpecherz.videorentalstore.exception.OrderValidationException;
import com.mpecherz.videorentalstore.model.VideoOrderRequest;
import com.mpecherz.videorentalstore.model.Order;
import com.mpecherz.videorentalstore.model.Rental;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderCreatorWithBonusProcessor orderCreatorWithBonusProcessor;
    private final OrderReturnProcessor orderReturnProcessor;

    @Override
    public List<Order> getAll() {
        return Collections.unmodifiableList(new ArrayList<>(orderRepository.getAllOrders()));
    }

    @Override
    public Order addOrder(VideoOrderRequest videoOrderRequest) {
        return orderCreatorWithBonusProcessor.process(videoOrderRequest);
    }

    @Override
    public Rental getRental(String orderId, String rentalId) {
        Order order = getOrderResource(orderId);
        return getRentalResource(rentalId, order);
    }

    @Override
    public Order returnRentals(String orderId, List<String> rentalIds) {
        return orderReturnProcessor.returnRentals(orderId, rentalIds);
    }

    @Override
    public List<Rental> getRentalsByOrderId(String orderId) {
        return Collections.unmodifiableList(new ArrayList<Rental>(orderRepository.getRentalsByOrderId(orderId)));
    }

    @Override
    public Order getOrder(String id) {
        return orderRepository.getOrder(id);
    }

    private Order getOrderResource(String orderId) {
        Optional<Order> orderObject = Optional.ofNullable(orderRepository.getOrder(orderId));
        return orderObject.orElseThrow(() -> new OrderValidationException(String.format("Order resource \'%s\' does not exist", orderId)));
    }

    private Rental getRentalResource(String rentalId, Order order) {
        if (order.getRentals().contains(rentalId)) {
            return orderRepository.getRental(rentalId);
        } else {
            throw new OrderValidationException(String.format("Rental resource \'%s\' does not exist", rentalId));
        }
    }
}

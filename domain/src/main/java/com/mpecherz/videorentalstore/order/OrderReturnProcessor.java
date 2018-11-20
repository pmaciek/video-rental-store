package com.mpecherz.videorentalstore.order;


import com.mpecherz.videorentalstore.exception.OrderValidationException;
import com.mpecherz.videorentalstore.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
class OrderReturnProcessor {
    protected final OrderRepository orderRepository;
    protected final PricePolicyResolver pricePolicyResolver;
    protected final CustomerService customerService;
    protected final VideoService videoService;

    Order returnRentals(String orderId, List<String> rentalIds) {
        Order order = getOrderResource(orderId);
        List<Rental> rentals = rentalIds.stream().map(id -> getRentalResource(id, order)).collect(toList());

        rentals.forEach(rental -> {
            checkIsReturned(rental);
            BigDecimal lateCharge = carculateLateCharge(rental);
            Rental returnedRental = createRental(rental, lateCharge);
            orderRepository.updateRental(returnedRental);
        });

        Order newOrder = createOrder(order, calculateTotalLateCharge(rentalIds));
        return orderRepository.updateOrder(newOrder);
    }

    private BigDecimal calculateTotalLateCharge(List<String> rentalIds) {
        return rentalIds.stream()
                .map(orderRepository::getRental)
                .map(Rental::getLateCharge)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    private long getDaysBetween(Rental rental) {
        Period period = Period.between(rental.getOrderDate(), LocalDate.now());
        return (long) period.getDays();
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

    private void checkIsReturned(Rental rental) {
        if (rental.isReturned()) {
            String message = format("Rental [%s] is already returned.", rental.getId());
            throw new IllegalStateException(format(message, rental.getId()));
        }
    }

    private BigDecimal carculateLateCharge(Rental rental) {
        long days = getDaysBetween(rental);
        if (days > rental.getNumberOfDays()) {
            Film.Type type = videoService.get(rental.getVideoId()).orElseGet(() -> {
                throw new IllegalStateException(format("Video resource %s not found", rental.getVideoId()));
            }).getType();
            return pricePolicyResolver.getPricePolicy(type).getPrice((int) days).min(rental.getPrice().negate());
        }
        return BigDecimal.ZERO;
    }

    private Order createOrder(Order order, BigDecimal totalLateCharge) {
        return Order.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .rentals(order.getRentals())
                .totalPrice(order.getTotalPrice())
                .totalLateCharge(totalLateCharge)
                .build();
    }

    private Rental createRental(Rental rental, BigDecimal lateCharge) {
        return Rental.builder()
                .id(rental.getId())
                .videoId(rental.getVideoId())
                .orderId(rental.getOrderId())
                .price(rental.getPrice())
                .lateCharge(lateCharge)
                .orderDate(rental.getOrderDate())
                .numberOfDays(rental.getNumberOfDays())
                .returnDate(LocalDate.now()).build();
    }
}

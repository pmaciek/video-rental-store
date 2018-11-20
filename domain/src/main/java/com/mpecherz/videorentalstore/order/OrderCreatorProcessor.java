package com.mpecherz.videorentalstore.order;


import com.mpecherz.videorentalstore.exception.OrderValidationException;
import com.mpecherz.videorentalstore.model.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
class OrderCreatorProcessor extends AbstractOrderCreatorProcessor {
    protected final OrderRepository orderRepository;
    protected final PricePolicyResolver pricePolicyResolver;
    protected final CustomerService customerService;
    protected final VideoService videoService;

    @Override
    protected Order process(VideoOrderRequest request) {
        Customer customerResource = getCustomer(request.getCustomerId());
        Map<String, Film> filmResourceMap = geFilmResourceMap(request);
        Order orderResource = saveOrder(customerResource);
        List<Rental> rentalResources = saveRentals(request, filmResourceMap, orderResource);
        return updateOrder(orderResource, rentalResources);
    }

    @Override
    protected BigDecimal getPrice(int daysToRent, Film.Type type) {
        return pricePolicyResolver.getPricePolicy(type).getPrice(daysToRent);
    }

    Customer getCustomer(String customerId) {
        return customerService.get(customerId).orElseThrow(getCustomerNotFoundValidationExceptionSupplier(customerId));
    }

    Film getFilmResource(String id) {
        return videoService.get(id).orElseThrow(getFilmNotFoundValidationExceptionSupplier(id));
    }

    private Map<String, Film> geFilmResourceMap(VideoOrderRequest videoOrderRequest) {
        return videoOrderRequest.getListOfVideos().stream()
                .map(item -> getFilmResource(item.getVideoId()))
                .collect(Collectors.toMap(Film::getId, Function.identity()));
    }

    private Order saveOrder(Customer customerResponse) {
        Order newOrder = createOrder(getUuid(), customerResponse.getId(), null, null);
        return orderRepository.saveOrder(newOrder);
    }

    private List<Rental> saveRentals(VideoOrderRequest request, Map<String, Film> filmResources, Order orderResource) {
        return request.getListOfVideos().stream()
                .map(item -> createNewRental(item, orderResource, filmResources.get(item.getVideoId())))
                .peek(orderRepository::saveRental)
                .collect(toList());
    }

    private Order updateOrder(Order orderResource, List<Rental> rentalResources) {
        List<String> rentalIds = rentalResources.stream().map(Rental::getId).collect(toList());
        BigDecimal totalPrice = rentalResources.stream().map(Rental::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        Order newOrderResource = createOrder(orderResource.getId(), orderResource.getCustomerId(), rentalIds, totalPrice);
        return orderRepository.updateOrder(newOrderResource);
    }

    private Supplier<OrderValidationException> getCustomerNotFoundValidationExceptionSupplier(String customerId) {
        return () -> new OrderValidationException(String.format("Customer resource \'%s\' does not exist", customerId));
    }

    private Supplier<OrderValidationException> getFilmNotFoundValidationExceptionSupplier(String filmId) {
        return () -> new OrderValidationException(String.format("Film resource \'%s\' does not exist", filmId));
    }

    private Rental createNewRental(VideoOrderItemRequest videoOrderItemRequest, Order order, Film videoResource) {
        return Rental.builder()
                .id(getUuid())
                .videoId(videoResource.getId())
                .orderId(order.getId())
                .price(getPrice(videoOrderItemRequest.getDaysToRent(), videoResource.getType()))
                .lateCharge(BigDecimal.ZERO)
                .orderDate(LocalDate.now())
                .numberOfDays(videoOrderItemRequest.getDaysToRent())
                .returnDate(null).build();
    }

    private Order createOrder(String orderId, String customerId, List<String> rentalIds, BigDecimal totalPrice) {
        return Order.builder()
                .id(orderId)
                .customerId(customerId)
                .rentals(rentalIds)
                .totalPrice(totalPrice)
                .build();
    }

    private String getUuid() {
        return UUID.randomUUID().toString();
    }
}

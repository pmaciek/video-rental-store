package com.mpecherz.videorentalstore.model;

import java.util.List;


public interface OrderService {

    List<Order> getAll();

    Order addOrder(VideoOrderRequest videoOrderRequest);

    Rental getRental(String orderId, String rentalId);

    List<Rental> getRentalsByOrderId(String orderId);

    Order getOrder(String id);

    Order returnRentals(String orderId, List<String> rentalIds);
}

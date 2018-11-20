package com.mpecherz.videorentalstore.order;


import com.mpecherz.videorentalstore.model.Order;
import com.mpecherz.videorentalstore.model.Rental;

import java.util.Collection;

interface OrderRepository {

    Collection<Order> getAllOrders();

    Order getOrder(String id);

    Order saveOrder(Order order);

    Order updateOrder(Order order);

    Rental getRental(String id);

    Rental updateRental(Rental rental);

    Rental saveRental(Rental rental);

    Collection getRentalsByOrderId(String id);


}

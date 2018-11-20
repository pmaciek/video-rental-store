package com.mpecherz.videorentalstore.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Collection;


@Getter
public class Order extends Resource {

    private final String customerId;
    private final Collection<String> rentals;
    private final BigDecimal totalPrice;
    private final BigDecimal totalLateCharge;

    @Builder
    public Order(String id, String customerId, Collection<String> rentals, BigDecimal totalPrice, BigDecimal totalLateCharge) {
        super(id);
        this.customerId = customerId;
        this.rentals = rentals;
        this.totalPrice = totalPrice;
        this.totalLateCharge = totalLateCharge;
    }
}

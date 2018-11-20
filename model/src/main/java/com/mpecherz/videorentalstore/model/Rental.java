package com.mpecherz.videorentalstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Getter
public class Rental extends Resource {

    private final String orderId;
    private final String videoId;
    private final LocalDate orderDate;
    private final int numberOfDays;
    private final BigDecimal price;
    private final BigDecimal lateCharge;
    private final LocalDate returnDate;

    @Builder
    public Rental(String id, String orderId, String videoId, LocalDate orderDate, int numberOfDays, BigDecimal price, BigDecimal lateCharge, LocalDate returnDate) {
        super(id);
        this.orderId = orderId;
        this.videoId = videoId;
        this.orderDate = orderDate;
        this.numberOfDays = numberOfDays;
        this.price = price;
        this.lateCharge = lateCharge;
        this.returnDate = returnDate;
    }

    @JsonIgnore
    public boolean isReturned() {
        return Objects.nonNull(getReturnDate());
    }

}

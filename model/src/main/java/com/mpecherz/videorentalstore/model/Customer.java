package com.mpecherz.videorentalstore.model;


import lombok.Getter;

@Getter
public class Customer extends Resource {
    private final int ffPoints;

    public Customer(String id, int ffPoints) {
        super(id);
        this.ffPoints = ffPoints;
    }
}

package com.mpecherz.videorentalstore.model;

import lombok.Getter;

@Getter
public class Film extends Resource {

    public enum Type {
        NEW, REGULAR, OLD
    }

    private final String name;
    private final Type type;

    public Film(String id, String name, Type type) {
        super(id);
        this.name = name;
        this.type = type;
    }
}

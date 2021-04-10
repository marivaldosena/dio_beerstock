package com.github.marivaldosena.beerstock.beers;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BeerRequest {
    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @NotNull
    @Size(min = 1, max = 200)
    private String brand;

    @NotNull
    @Max(500)
    private Integer max;

    @NotNull
    @Max(100)
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private BeerType type;

    public BeerRequest(String name, String brand, Integer max, Integer quantity, BeerType type) {
        this.name = name;
        this.brand = brand;
        this.max = max;
        this.quantity = quantity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getMax() {
        return max;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BeerType getType() {
        return type;
    }

    public Beer toEntity() {
        return new Beer(name, brand, max, quantity, type);
    }
}

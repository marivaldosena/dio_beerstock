package com.github.marivaldosena.beerstock.beers;

public class BeerRequestBuilder {
    private String name;
    private String brand;
    private Integer max;
    private Integer quantity;
    private BeerType type;

    public BeerRequestBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public BeerRequestBuilder withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public BeerRequestBuilder withMax(Integer max) {
        this.max = max;
        return this;
    }

    public BeerRequestBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BeerRequestBuilder withType(BeerType type) {
        this.type = type;
        return this;
    }

    public BeerRequest build() {
        return new BeerRequest(name, brand, max, quantity, type);
    }
}

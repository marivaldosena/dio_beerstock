package com.github.marivaldosena.beerstock.beers;

public class BeerDto {
    private String name;
    private String brand;
    private Integer max;
    private Integer quantity;
    private String type;

    public BeerDto(Beer beer) {
        this.name = beer.getName();
        this.brand = beer.getBrand();
        this.max = beer.getMax();
        this.quantity = beer.getQuantity();
        this.type = beer.getType().getDescription();
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

    public String getType() {
        return type;
    }
}

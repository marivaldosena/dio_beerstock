package com.github.marivaldosena.beerstock.beers;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class QuantityDto {
    @NotNull
    @Max(100)
    private Integer quantity;

    public QuantityDto(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

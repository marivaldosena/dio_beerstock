package com.github.marivaldosena.beerstock.errors;

public class BeerStockExceededException extends CustomErrorType {
    public BeerStockExceededException(Long id, int quantityToIncrement) {
        super(String.format("Beers to increment with %s ID exceeds max stock capacity: %s", id, quantityToIncrement));
    }

    @Override
    public String getErrorCode() {
        return "stock-exceeded";
    }
}

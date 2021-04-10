package com.github.marivaldosena.beerstock.errors;

public class BeerNotFoundException extends CustomErrorType {
    public BeerNotFoundException(String beerName) {
        super(String.format("Beer with name %s not found.", beerName));
    }

    public BeerNotFoundException(Long id) {
        super(String.format("Beer with id %s not found.", id));
    }

    @Override
    public String getErrorCode() {
        return "not-found";
    }
}

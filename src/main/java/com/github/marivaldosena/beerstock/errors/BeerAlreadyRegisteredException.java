package com.github.marivaldosena.beerstock.errors;

public class BeerAlreadyRegisteredException extends CustomErrorType {
    public BeerAlreadyRegisteredException(String beerName) {
        super(String.format("Beer with name %s is already registered.", beerName));
    }

    @Override
    public String getErrorCode() {
        return "duplicate";
    }
}

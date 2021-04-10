package com.github.marivaldosena.beerstock.errors;

public class CustomErrorType extends RuntimeException {
    public CustomErrorType(String message) {
        super(message);
    }

    public String getErrorCode() {
        return "error";
    }
}

package com.github.marivaldosena.beerstock.errors;

public class ErrorDto {
    private String source;
    private String message;

    public ErrorDto(String source, String message) {
        this.source = source;
        this.message = message;
    }

    public String getSource() {
        return source;
    }

    public String getMessage() {
        return message;
    }
}

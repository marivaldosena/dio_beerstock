package com.github.marivaldosena.beerstock.errors;

import java.util.List;

public class ErrorsListDto {
    private List<ErrorDto> errors;

    public ErrorsListDto(List<ErrorDto> errors) {
        this.errors = errors;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }
}

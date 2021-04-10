package com.github.marivaldosena.beerstock.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErrorHandlerController {
    private final MessageSource messageSource;

    @Autowired
    public ErrorHandlerController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomErrorType.class)
    public ErrorsListDto handle(CustomErrorType exception) {
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(new ErrorDto(exception.getErrorCode(), exception.getMessage()));
        return new ErrorsListDto(errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorsListDto handle(MethodArgumentNotValidException exception) {
        List<ErrorDto> dtos = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            dtos.add(new ErrorDto(e.getField(), message));
        });

        return new ErrorsListDto(dtos);
    }
}

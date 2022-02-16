package com.joshaby.springboot2backend.controllers.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ValidationError extends StandartError {

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String message, Long time) {
        super(status, message, time);
    }

    public void addError(String field, String message) {
        errors.add(new FieldMessage(field, message));
    }
}

package com.joshaby.springboot2backend.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FieldMessage {

    private String fiel;

    private String message;
}

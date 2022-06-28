package com.joshaby.springboot2backend.services.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClienteInsertValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {

    String message() default "CPF ou CNPJ inválido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

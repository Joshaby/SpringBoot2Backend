package com.joshaby.springboot2backend.services.validation;

import com.joshaby.springboot2backend.controllers.exceptions.FieldMessage;
import com.joshaby.springboot2backend.dto.ClienteDTO1;
import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO1> {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteDTO1 dto, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> errors = new ArrayList<>();
        Map<String, String> uriVariables =
                (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer id = Integer.parseInt(uriVariables.get("id"));
        Cliente cliente = repository.findByEmail(dto.getEmail());
        if (cliente != null && !cliente.getId().equals(id)) {
            errors.add(new FieldMessage("email", "Email já existe"));
        }
        for (FieldMessage error : errors) {
            constraintValidatorContext.getDefaultConstraintMessageTemplate();
            constraintValidatorContext.buildConstraintViolationWithTemplate(error.getMessage())
                    .addPropertyNode(error.getField()).addConstraintViolation();
        }
        return errors.isEmpty();
    }
}

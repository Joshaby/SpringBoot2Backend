package com.joshaby.springboot2backend.services.validation;

import com.joshaby.springboot2backend.controllers.exceptions.FieldMessage;
import com.joshaby.springboot2backend.dto.ClienteDTO2;
import com.joshaby.springboot2backend.entities.Cliente;
import com.joshaby.springboot2backend.entities.enums.TipoCliente;
import com.joshaby.springboot2backend.repositories.ClienteRepository;
import com.joshaby.springboot2backend.services.utils.ServicesUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTO2> {

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteDTO2 dto, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> errors = new ArrayList<>();
        if (dto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getTipo())
                && !ServicesUtils.isCPF(dto.getCpfOuCnpj())) {
            errors.add(new FieldMessage("cpfOuCnpj", "CPF inválido!"));
        }
        if (dto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getTipo())
                && !ServicesUtils.isCNPJ(dto.getCpfOuCnpj())) {
            errors.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido!"));
        }
        Cliente cliente = repository.findByEmail(dto.getEmail());
        if (cliente != null) {
            errors.add(new FieldMessage("email", "Email já existe!"));
        }
        for (FieldMessage error : errors) {
            constraintValidatorContext.getDefaultConstraintMessageTemplate();
            constraintValidatorContext.buildConstraintViolationWithTemplate(error.getMessage())
                    .addPropertyNode(error.getField()).addConstraintViolation();
        }
        return errors.isEmpty();
    }
}

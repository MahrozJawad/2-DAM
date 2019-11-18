
package com.modelos;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class modeloValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return marcas.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "nombreModelo",
                "required.nombreModelo",
                "El nombre del modelo es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "stock",
                "required.stock",
                "El Stock es obligatorio");
        
    }
}

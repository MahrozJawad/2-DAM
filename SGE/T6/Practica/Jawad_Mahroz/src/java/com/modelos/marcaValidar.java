
package com.modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class marcaValidar implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return marcas.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "nombreMarca",
                "required.nombreMarca",
                "El nombre de la marca es obligatorio");
        
    }
}

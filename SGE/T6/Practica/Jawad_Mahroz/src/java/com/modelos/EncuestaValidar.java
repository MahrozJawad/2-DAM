
package com.modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class EncuestaValidar implements Validator{

    private static final String PatronVacio = "[ ]*";
private Pattern pattern;
private Matcher matcher;
    
    @Override
    public boolean supports(Class<?> type) {
        return Preguntas.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Preguntas pregunta = (Preguntas)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "textoPregunta",
                "required.textoPregunta",
                "El texto de pregunta es obligatorio");
        if (!(pregunta.getTextoPregunta()!= null)) {
            
            this.pattern = Pattern.compile(PatronVacio);
            this.matcher = pattern.matcher(pregunta.getTextoPregunta());
            if (!matcher.matches()) {
            errors.rejectValue("textoPregunta", "textoPregunta.incorrect",
            "El E-Mail "+pregunta.getTextoPregunta()+" no es válido");
            }
        }
        
    }
}

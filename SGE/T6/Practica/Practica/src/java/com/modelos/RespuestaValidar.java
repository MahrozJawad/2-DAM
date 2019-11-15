
package com.modelos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class RespuestaValidar implements Validator{

    private static final String PatronVacio = "[ ]*";   
private Pattern pattern;
private Matcher matcher;
    
    @Override
    public boolean supports(Class<?> type) {
        return Respuesta.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Respuesta persona = (Respuesta)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "textoRespuesta",
                "required.textoRespuesta",
                "El texto de respuesta es obligatorio");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                "numeroRespuestas",
                "required.numeroRespuestas",
                "El número de respuesta es obligatorio");
//        
//        if (!(persona.getTextoRespuesta()!= null && persona.getTextoRespuesta().isEmpty())) {
//            
//            this.pattern = Pattern.compile(PatronVacio);
//            this.matcher = pattern.matcher(persona.getTextoRespuesta());
//            if (matcher.matches()) {
//            errors.rejectValue("textoRespuesta", "correo.incorrect",
//            "El texto de respuesta no es válido");
//            }
//        }
        
    }
}

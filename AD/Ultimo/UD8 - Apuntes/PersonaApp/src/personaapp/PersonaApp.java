
package personaapp;

import com.dam.persona.Domicilio;
import com.dam.persona.Persona;

public class PersonaApp {

    public static void main(String[] args) {
        Persona persona = new Persona(21444555, "Sergio", "Fernández", "61277788", 
                            new Domicilio("C/Jaume de Scals, 35","03100","Xixona","Alicante"));
        
        System.out.println(persona.toString());
    }
    
}

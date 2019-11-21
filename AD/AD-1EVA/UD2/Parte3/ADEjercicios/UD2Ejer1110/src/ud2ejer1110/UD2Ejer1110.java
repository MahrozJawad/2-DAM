package ud2ejer1110;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UD2Ejer1110 {

    public static void main(String[] args) throws JAXBException {
// P1
        JAXBContext context = JAXBContext.newInstance(TodasPersonas.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        TodasPersonas todasPersonas = (TodasPersonas) unmarshaller.unmarshal(new File("./datos/personas.xml"));
        
// P2
        ArrayList<Persona> personas = todasPersonas.getPersonas();
        for (Persona per : personas) {
            System.out.println(per.getNombre()+ " - " + per.getEmail()+ " - " + per.getEdad());
        }
    }
}

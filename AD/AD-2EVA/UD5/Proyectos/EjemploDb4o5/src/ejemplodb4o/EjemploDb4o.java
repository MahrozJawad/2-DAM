package ejemplodb4o;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import java.util.List;


public class EjemploDb4o {

    public static void main(String[] args) throws Exception {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile("./datos/personas.db4o");
// Buscamos las personas de más de 17 años
            List<Persona> adultos = db.query(new Predicate<Persona>() {
                @Override
                public boolean match(Persona candidato) {
                    return candidato.getEdad() > 17;
                }
            });
            for (Persona adulto : adultos) {
                System.out.println("Encontrado " + adulto.getNombre() + " "
                        + adulto.getApellidos() + ": " + adulto.getEdad());
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}

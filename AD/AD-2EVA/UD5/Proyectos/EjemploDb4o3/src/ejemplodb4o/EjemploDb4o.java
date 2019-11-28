package ejemplodb4o;

import com.db4o.*;

public class EjemploDb4o {

    public static void main(String[] args) throws Exception {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile("./datos/personas.db4o");
// Añadimos otro "Juan"
            Persona p = new Persona("A", "B", 30);
// Vamos a intentar guardar 10 veces el mismo dato
            for (int i = 1; i <= 10; i++) {
                if (db.queryByExample(p).hasNext() == false) {
// Si no existe, guardamos
                    db.store(p);
                    db.commit();
                }
            }
// Y finalmente buscamos todos los apellidos "B"
            ObjectSet<Persona> lista = db.queryByExample(new Persona(null, "B", 0));
            while (lista.hasNext()) {
                System.out.println(lista.next());
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}

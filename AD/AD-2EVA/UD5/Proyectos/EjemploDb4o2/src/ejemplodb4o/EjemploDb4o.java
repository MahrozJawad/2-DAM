package ejemplodb4o;

import com.db4o.*;

public class EjemploDb4o {

    public static void main(String[] args) throws Exception {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile("./datos/personas.db4o");
// Añadimos otro "Juan"
            Persona p = new Persona("Juan", "Pérez", 21);
            db.store(p);
            db.commit();
// Mostrar número de registros
            System.out.println("BD con "
                    + db.queryByExample(new Persona(null, null, 0)).size() + " registros");
// Buscamos todos los "Juan" (el campo con valor null o 0 no se filtra)
            ObjectSet<Persona> juanes = db.queryByExample(new Persona("Juan", null, 0));
            while (juanes.hasNext()) {
                System.out.println(juanes.next());
            }
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}

package ejemplodb4o;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class EjemploDb4o {

    public static void main(String[] args) throws Exception {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile("./datos/personas.db4o");
            Persona p1 = new Persona("Juan", "López", 22);
            Persona p2 = new Persona("José", "Álvarez", 25);
            db.store(p1);
            db.store(p2);
            db.commit();
            System.out.println("BD creada");
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}

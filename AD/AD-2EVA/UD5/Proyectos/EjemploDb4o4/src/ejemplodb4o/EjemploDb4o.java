package ejemplodb4o;

import com.db4o.*;

public class EjemploDb4o {

    public static void main(String[] args) throws Exception {
        ObjectContainer db = null;
        try {
            db = Db4o.openFile("./datos/personas.db4o");
// Vamos a modificar la edad de la ficha de "A B"
            ObjectSet<Persona> listamod = db.queryByExample(new Persona("A", "B", 0));
            while (listamod.hasNext()) {
                Persona a = (Persona) listamod.next();
                a.setEdad(a.getEdad() + 1);
                db.store(a);
                db.commit();
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

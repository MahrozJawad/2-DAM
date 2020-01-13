package personaappsql;

import com.dam.persona.ListaPersonas;

public class PersonaAppSql {

    public static void main(String[] args) {
        ListaPersonas lista = new ListaPersonas();
        lista.cargaSql();
        for (int i = 0; i < lista.getLista().size(); i++) {
            System.out.println(lista.getLista().get(i).toString());
        }
    }
}

package ud2json04serializargson;

import com.google.gson.Gson;
import java.util.ArrayList;

public class UD2Json04SerializarGson {

    public static void main(String[] args) {
// Crear objeto de la clase Users
        Users userData = new Users(1, "Juan");
        Gson gson = new Gson();
// Serializar objeto (Fichero a String)
        String userDataString = gson.toJson(userData);
        System.out.println(userDataString);
// Deserializar objeto (String a Objeto)
        String userJson = "{'UserId':2, 'UserName':'Sergio'}";
        Users userObject = gson.fromJson(userJson, Users.class);
        System.out.println(userObject.toString());
// Serializar una lista de objetos utilizando la combinación de:
// gson.toJson que usa a su vez User.toString()
        ArrayList<Users> lista = new ArrayList<>();
        lista.add(new Users(3, "Ana"));
        lista.add(new Users(4, "María"));
        String userListaString = gson.toJson(lista);
        System.out.println(userListaString);
    }
}

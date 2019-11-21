package packUtils;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static packUtils.UtilString.*;
import static Clases.TiendasJson.JsonProvincia;
import java.io.IOException;

public class Menu {

    public static void Mostrar() throws SQLException, ClassNotFoundException, IOException {

        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    

        while (!salir) {
            Linea();
            System.out.print(Colores.FONDO_BLANCO);
            System.out.println(Colores.LETRA_BLANCO + "1. JSON de Alicante");
            System.out.println(Colores.LETRA_BLANCO + "2. JSON de Valencia");
            System.out.println(Colores.LETRA_BLANCO + "3. JSON de Castellón");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-', 20));
            System.out.println(Colores.LETRA_BLANCO + "0. Salir");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-', 20));

            try {

                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = entrada.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        JsonProvincia("Alicante");
                        break;
                    case 2:
                        JsonProvincia("Valencia");
                        break;
                    case 3:
                        JsonProvincia("Castellón");
                        break;

                    // **************************
                    // SALIR
                    // **************************
                    case 0:
                        salir = true;
                        System.out.println("Terminado");
                        Linea();
                        break;
                    default:
                        System.out.println(Colores.LETRA_ROJO + Colores.FONDO_AMARILLO + "Opción no válida");
                        break;
                }
            } catch (InputMismatchException e) {
                Linea();
                System.out.println(Colores.LETRA_ROJO + Colores.FONDO_AMARILLO + "Debe insertar un número");
                entrada.next();
            }
        }

    }

}

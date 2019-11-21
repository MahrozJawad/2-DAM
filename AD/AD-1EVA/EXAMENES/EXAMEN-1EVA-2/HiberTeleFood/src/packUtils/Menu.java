package packUtils;

import static hibertelefood.HiberTeleFood.AñadirEmail;
import static hibertelefood.HiberTeleFood.CambiaEncargado;
import static hibertelefood.HiberTeleFood.MostrarTienda;
import java.util.InputMismatchException;
import java.util.Scanner;
import static packUtils.UtilString.*;

public class Menu {

    public static void Mostrar() {

        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    

        while (!salir) {
            Linea();
            System.out.print(Colores.FONDO_BLANCO);
            System.out.println(Colores.LETRA_BLANCO + "1. Mostrar Tiendas");
            System.out.println(Colores.LETRA_BLANCO + "2. Cambiar encargado de Tienda");
            System.out.println(Colores.LETRA_BLANCO + "3. Añadir email a tienda");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-', 20));
            System.out.println(Colores.LETRA_BLANCO + "0. Salir");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-', 20));

            try {

                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = entrada.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        MostrarTienda();
                        break;
                    case 2:
                        CambiaEncargado();
                        break;
                    case 3:
                        AñadirEmail();
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

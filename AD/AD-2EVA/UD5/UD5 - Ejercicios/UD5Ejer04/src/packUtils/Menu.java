
package packUtils;

import java.util.InputMismatchException;
import java.util.Scanner;
import static packUtils.UtilString.*;
import ud5ejer01.UD5Ejer01;
import static ud5ejer01.UD5Ejer01.AnadirLibro;
import static ud5ejer01.UD5Ejer01.BuscarLibros;
import static ud5ejer01.UD5Ejer01.VerLibros;

public class Menu {

    public static void Mostrar() {
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    
        
        while (!salir) {
            Linea();
            System.out.print(Colores.FONDO_BLANCO);
            System.out.println(Colores.LETRA_BLANCO + "1. Añadir libros");
            System.out.println(Colores.LETRA_BLANCO + "2. Ver libros");
            System.out.println(Colores.LETRA_BLANCO + "3. buscar libros por titulo");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_BLANCO + "0. Salir");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = sn.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        AnadirLibro();
                        break;  
                    case 2:
                        VerLibros();
                        break;  
                    case 3:
                        BuscarLibros();
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
                sn.next();
            }
        }               
              
    }
    
    
    
    
}

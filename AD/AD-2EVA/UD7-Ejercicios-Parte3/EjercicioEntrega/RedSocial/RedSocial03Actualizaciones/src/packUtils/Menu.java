
package packUtils;

import java.util.InputMismatchException;
import java.util.Scanner;
import static packUtils.UtilString.*;
import static RedSocial03Actualizaciones.RedSocial03Actualizaciones.*;

public class Menu {

    public static void Mostrar() {
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    
        
        while (!salir) {
            Linea();
            System.out.print(Colores.FONDO_BLANCO);
            System.out.println(Colores.LETRA_BLANCO + "1. Quitar campos fecha");
            System.out.println(Colores.LETRA_BLANCO + "2. Añadir campos cantidad_amigos");
            System.out.println(Colores.LETRA_BLANCO + "3. Añadir comentario a Daniela");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_BLANCO + "0. Salir");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = sn.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        QuitarCamposFecha();
                        break;  
                    case 2:
                        AñadirCamposCantidad_amigos();
                        break;  
                    case 3:
                        AñadirComentarioADaniela();
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

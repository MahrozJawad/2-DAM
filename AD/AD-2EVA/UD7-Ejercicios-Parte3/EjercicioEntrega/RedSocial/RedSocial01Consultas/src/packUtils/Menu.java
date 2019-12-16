
package packUtils;

import java.util.InputMismatchException;
import java.util.Scanner;
import static packUtils.UtilString.*;
import redsocial01consultas.*;

public class Menu {

    public static void Mostrar() {
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    
        
        while (!salir) {
            Linea();
            System.out.print(Colores.FONDO_BLANCO);
            System.out.println(Colores.LETRA_BLANCO + "1. Mujeres (activo=true)");
            System.out.println(Colores.LETRA_BLANCO + "2. Hombres (total_mensajes>1)");
            System.out.println(Colores.LETRA_BLANCO + "3. Usuarios de fútbol o cine");
            System.out.println(Colores.LETRA_BLANCO + "4. Usuarios de fútbol y cine");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_BLANCO + "0. Salir");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = sn.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        RedSocial01Consultas.MujeresActivas();
                        break;  
                    case 2:
                        System.out.println("Opción 2");
                        break;  
                    case 3:
                        System.out.println("Opción 3");
                        break;  
                    case 4:
                        System.out.println("Opción 4");
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

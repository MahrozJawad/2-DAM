
package packUtils;

import static hibertelefood.HiberTeleFood.addEmailToTienda;
import static hibertelefood.HiberTeleFood.cambiarEncargado;
import static hibertelefood.HiberTeleFood.verTiendas;
import java.util.InputMismatchException;
import java.util.Scanner;
import static packUtils.UtilString.*;

public class Menu {

    public static void Mostrar() {
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    
        
        while (!salir) {
            Linea();
            System.out.print(Colores.FONDO_BLANCO);
            System.out.println(Colores.LETRA_AZUL + "1. Mostrar Tiendas");
            System.out.println(Colores.LETRA_AZUL + "2. Cambiar encargado de Tienda");
            System.out.println(Colores.LETRA_AZUL + "3. Añadir email a tienda");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_AZUL + "0. Salir");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = sn.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        verTiendas();
                        break;  
                    case 2:
                        cambiarEncargado();
                        break;  
                    case 3:
                        addEmailToTienda();
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

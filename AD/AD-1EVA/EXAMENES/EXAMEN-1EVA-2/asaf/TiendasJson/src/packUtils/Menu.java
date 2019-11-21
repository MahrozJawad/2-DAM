
package packUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static packUtils.UtilString.*;
import static tiendasjson.TiendasJson.jsonTiendas;

public class Menu {

    public static void Mostrar() throws ClassNotFoundException, SQLException, IOException {
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    
        
        while (!salir) {
            Linea();
            System.out.print(Colores.FONDO_BLANCO);
            System.out.println(Colores.LETRA_AZUL + "1. JSON de Alicante");
            System.out.println(Colores.LETRA_AZUL + "2. JSON de Valencia");
            System.out.println(Colores.LETRA_AZUL + "3. JSON de Castellón");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_AZUL + "0. Salir");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = sn.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        jsonTiendas("Alicante");
                        break;  
                    case 2:
                        jsonTiendas("Valencia");
                        break;  
                    case 3:
                        jsonTiendas("Castellón");
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

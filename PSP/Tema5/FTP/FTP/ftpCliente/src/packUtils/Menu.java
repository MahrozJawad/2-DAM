
package packUtils;

import static ftpcliente.FTPCliente.*;
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
            System.out.println(Colores.LETRA_BLANCO + "1. Renombrar directorios o ficheros");
            System.out.println(Colores.LETRA_BLANCO + "2. Subir un fichero");
            System.out.println(Colores.LETRA_BLANCO + "3. Descargar un fichero");
            System.out.println(Colores.LETRA_BLANCO + "4. Listar un directorio");
            System.out.println(Colores.LETRA_BLANCO + "5. Eliminar fichero");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_AZUL + "0. Salir");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = sn.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        RenombrarDirectorioOFichero();
                        break;  
                    case 2:
                        SubirUnFichero();
                        break;  
                    case 3:
                        DescargarUnFichero();
                        break;  
                    case 4:
                        ListarDirectorio();
                        break;  
                    case 5:
                        EliminarFichero();
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

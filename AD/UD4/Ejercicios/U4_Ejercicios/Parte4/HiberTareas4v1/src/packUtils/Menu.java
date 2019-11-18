
package packUtils;

import static HiberTareas4v1.HiberTareas4v1.addTarea;
import static HiberTareas4v1.HiberTareas4v1.buscarTareasPorDescripcion;
import static HiberTareas4v1.HiberTareas4v1.verTareas;
import static HiberTareas4v1.HiberTareas4v1.verTareasPendiente;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import static packUtils.UtilString.*;

public class Menu {

    public static void Mostrar() throws ParseException {
        
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    
        
        while (!salir) {
            Linea();
            System.out.print(Colores.FONDO_BLANCO);
            System.out.println(Colores.LETRA_AZUL + "1. Ver Tareas");
            System.out.println(Colores.LETRA_AZUL + "2. Añadir Tarea");
            System.out.println(Colores.LETRA_AZUL + "3. Ver Tareas pendientes próximos 3 días");
            System.out.println(Colores.LETRA_AZUL + "4. Buscar tarea por descripción");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_AZUL + "0. Salir");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = entrada.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        verTareas();
                        break;  
                    case 2:
                        addTarea();
                        break;  
                    case 3:
                        verTareasPendiente();
                        break;  
                    case 4:
                        buscarTareasPorDescripcion();
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


package packUtils;

import static HiberSeries3v3.HiberSeries3v3.addActor;
import static HiberSeries3v3.HiberSeries3v3.addCanal;
import static HiberSeries3v3.HiberSeries3v3.addParticipante;
import static HiberSeries3v3.HiberSeries3v3.addSerie;
import static HiberSeries3v3.HiberSeries3v3.eliminarSerie;
import static HiberSeries3v3.HiberSeries3v3.modificarCanal;
import static HiberSeries3v3.HiberSeries3v3.modificarSerie;
import static HiberSeries3v3.HiberSeries3v3.verActor;
import static HiberSeries3v3.HiberSeries3v3.verCanalesTV;
import static HiberSeries3v3.HiberSeries3v3.verSeries;
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
            System.out.println(Colores.LETRA_BLANCO + "1. Ver Series");
            System.out.println(Colores.LETRA_BLANCO + "2. Ver Canales de TV");
            System.out.println(Colores.LETRA_BLANCO + "3. Añadir canal de TV");
            System.out.println(Colores.LETRA_BLANCO + "4. Añadir Serie");
            System.out.println(Colores.LETRA_BLANCO + "5. Modificar Serie");
            System.out.println(Colores.LETRA_BLANCO + "6. Modificar canal de TV");
            System.out.println(Colores.LETRA_BLANCO + "7. Eliminar Serie");
            System.out.println(Colores.LETRA_BLANCO + "8. Añadir actor");
            System.out.println(Colores.LETRA_BLANCO + "9. Añadir participación");
            System.out.println(Colores.LETRA_BLANCO + "10. Ver Actores");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_BLANCO + "0. Salir");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = entrada.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        verSeries();
                        break;  
                    case 2:
                        verCanalesTV();
                        break;  
                    case 3:
                        addCanal();
                        break;  
                    case 4:
                        addSerie();
                        break;  
                    case 5:
                        modificarSerie();
                        break;  
                    case 6:
                        modificarCanal();
                        break;  
                    case 7:
                        eliminarSerie();
                        break;  
                    case 8:
                        addActor();
                        break;  
                    case 9:
                        addParticipante();
                        break;  
                    case 10:
                        verActor();
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

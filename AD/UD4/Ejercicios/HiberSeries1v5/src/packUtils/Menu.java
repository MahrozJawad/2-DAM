
package packUtils;

import hiberseries1v1.HiberSeries1v1;
import static hiberseries1v1.HiberSeries1v1.addSerie;
import static hiberseries1v1.HiberSeries1v1.buscarSeriePorDuracion;
import static hiberseries1v1.HiberSeries1v1.mostrarSeriesPorCanal;
import static hiberseries1v1.HiberSeries1v1.verSeries;
import static hiberseries1v1.HiberSeries1v1.verSeriestextoIntroducido;
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
            System.out.println(Colores.LETRA_BLANCO + "1. Mostrar series");
            System.out.println(Colores.LETRA_BLANCO + "2. Añadir serie");
            System.out.println(Colores.LETRA_BLANCO + "3. Buscar Serie por título");
            System.out.println(Colores.LETRA_BLANCO + "4. Buscar Serie por duración");
            System.out.println(Colores.LETRA_BLANCO + "5. Mostrar series por Canal");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_BLANCO + "0. Salir");
            System.out.println(Colores.LETRA_BLANCO + UtilString.StrRepetir('-',20));

            try {
                
                System.out.print(Colores.LETRA_NEGRO + "Escribe una de las opciones: ");
                opcion = sn.nextInt();

                Linea();
                switch (opcion) {
                    case 1:
                        verSeries();
                        break;   
                        
                    case 2:
                        addSerie();
                        break; 
                    case 3:
                        verSeriestextoIntroducido();
                        break; 
                    case 4:
                        buscarSeriePorDuracion();
                        break;
                    case 5:
                        mostrarSeriesPorCanal();
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


package packUtils;

import static HiberSeries2v5.HiberSeries2v5.addCanal;
import static HiberSeries2v5.HiberSeries2v5.addSerie;
import static HiberSeries2v5.HiberSeries2v5.eliminarSerie;
import static HiberSeries2v5.HiberSeries2v5.modificarCanal;
import static HiberSeries2v5.HiberSeries2v5.modificarSerie;
import static HiberSeries2v5.HiberSeries2v5.verCanalesTV;
import static HiberSeries2v5.HiberSeries2v5.verSeries;
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
            System.out.println(Colores.LETRA_AZUL + "1. Ver Series");
            System.out.println(Colores.LETRA_AZUL + "2. Ver Canales de TV");
            System.out.println(Colores.LETRA_AZUL + "3. Añadir canal de TV");
            System.out.println(Colores.LETRA_AZUL + "4. Añadir Serie");
            System.out.println(Colores.LETRA_AZUL + "5. Modificar Serie");
            System.out.println(Colores.LETRA_AZUL + "6. Modificar canal de TV");
            System.out.println(Colores.LETRA_AZUL + "7. Eliminar Serie");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));
            System.out.println(Colores.LETRA_AZUL + "0. Salir");
            System.out.println(Colores.LETRA_AZUL + UtilString.StrRepetir('-',20));

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

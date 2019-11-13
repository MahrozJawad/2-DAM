
package ejercicio2;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Comensal extends Thread{
    private int nombreComensal;
    private int quiereBeber;
    private int queda;
    private Jarra jarra;
    private Camarero camarero;
    private boolean EstaEnBar;
    private double cantidadMaximabebido = 0;
    private boolean quedaCerveza = true;
    private int aux = 0; //Para que haga vueltas cuando termine la cerveza:

    public Comensal(int nombreComensal, Camarero c, Jarra j) {
        this.nombreComensal = nombreComensal;
        System.out.println("Comensal " + nombreComensal + " llega en el bar");
        jarra = j;
        camarero = c;
        jarra.capacidad = 600;
        EstaEnBar = true;
    }
    
    
    
    
    public synchronized void run() {
        try {
            while(quedaCerveza)
            {
                    Thread.sleep(200);
                quiereBeber = (int) (Math.random() * ((400 - 150) + 1)) + 150;
                System.out.println("Comensal " + nombreComensal + " quiere beber " + quiereBeber);
                if(jarra.getCapacidad()< quiereBeber) {
                    System.out.println("Comensal "+nombreComensal+" tiene que esperar porque quiere beber " + quiereBeber + " y la jarra tiene " + jarra.getCapacidad());

                    if(camarero.isDespierta()) {
                        System.out.println("El camarero se despierta de su manisiesta y rellena y sigue trabajando");
                        jarra.setCapacidad(600);
                    }
                }
                else
                {
                    queda = jarra.getCapacidad() - quiereBeber;
                    jarra.setCapacidad(queda);
                    System.out.println("Comensal " + nombreComensal + " bebe y queda " + queda);
                    cantidadMaximabebido += quiereBeber;
                }
                if (aux++ == 2) {
                    quedaCerveza = false; // para que termine el programa
                    //camarero.destroy();
                }
                
                
            }
            System.out.println("el comensal "+ nombreComensal + " se va porque ya no queda suficiente Cerveza y no hay camarero despues de haber tomado " + cantidadMaximabebido);
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Comensal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

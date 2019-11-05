
package banco;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona extends Thread{
    private String nombre;
    private Cuenta cuenta;
    
    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            double a = (double)(Math.random() * ((500 - 1) + 1)) + 1;
            double aleatorio = (double)Math.round(a*100)/100;
            cuenta.setIngreso(aleatorio);
        }
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int i = 0; i < 2; i++) {
            double a = (double)(Math.random() * ((500 - 1) + 1)) + 1;
            double aleatorio = (double)Math.round(a*100)/100;
            cuenta.setReintegro(aleatorio);
        }
    }

    public Persona(String nombre, Cuenta c) {
        this.nombre = nombre;
        cuenta = c;
    }

    
    
}


package ejercicio2;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Camarero extends Thread{

    public boolean despierta = false;
    
    
    public void run() {
        while (true) {

            try {
                Thread.sleep(3000);
                if (!despierta) {
                    setDespierta(true);
                }
                else
                    setDespierta(false);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Camarero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isDespierta() {
        return despierta;
    }

    public void setDespierta(boolean despierta) {
        this.despierta = despierta;
    }
}

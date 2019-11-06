package controldestock;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Envio extends Thread {

    private int piezasLlegadas;
    private Retirada r;
    private Almacen a;

    @Override
    public void run() {

        while (!a.error()) {
            try {
                this.piezasLlegadas = (int) (Math.random() * ((1000 - 400) + 1)) + 400;
                System.out.println("Llegan " + piezasLlegadas + " piezas");
                r.setStock(r.getStock()+piezasLlegadas);
                a.Llegadas(piezasLlegadas, r.getStock());
                if(!a.error()) {
                    System.out.println("Hay " + r.getStock() + " Piezas en el almacen");
                }
                    
                
                
                sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(Envio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public Envio(Retirada r, Almacen a) {
        this.r = r;
        this.a = a;
    }

    public int getPiezasLlegadas() {
        return piezasLlegadas;
    }

    public void setPiezasLlegadas(int piezasLlegadas) {
        this.piezasLlegadas = piezasLlegadas;
    }

}

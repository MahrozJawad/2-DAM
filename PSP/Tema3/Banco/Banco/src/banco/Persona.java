package banco;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona extends Thread {

    protected String nombre;
    private Cuenta cuenta;

    @Override
    public void run() {
            
            while (!cuenta.isError()) {         
                try {
                    cuenta.setIngreso(nNumeroAleatorio(), this);
                    Thread.sleep(1000);
                    
                    //cuenta.setIngreso(nNumeroAleatorio(), this);
                    //Thread.sleep(1000);
                    
                    //cuenta.setReintegro(nNumeroAleatorio(),this);
                    //Thread.sleep(1000);
                    
                    cuenta.setReintegro(nNumeroAleatorio(),this);
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

    }

    public Persona(String nombre, Cuenta c) {
        this.nombre = nombre;
        cuenta = c;
    }
    public double nNumeroAleatorio() throws InterruptedException
    {
        double a = (double)(Math.random()*(500-1)+1);
            double aleatorio = (double) Math.round(a * 100) / 100;
            return aleatorio;
    }
    

}

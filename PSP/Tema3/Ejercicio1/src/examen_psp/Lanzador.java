
package examen_psp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Lanzador extends Thread{

    private String[] array;
    
    public Lanzador(String[] array) {
        this.array = array;
        
    }
    
    public synchronized void run() {
        for (int i = 0; i < array.length; i++) {
            try {
                Thread.sleep(50);
                System.out.println("Hilo: " + this.getName() + " " + "COMANDO: " + array[i]);
                Process p = new ProcessBuilder(array[i]).start();
                InputStream is = p.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                
                BufferedReader br = new BufferedReader(isr);
                
                String linea;
                while ((linea=br.readLine()) != null) {
                    System.out.println(this.getName()+ "-->:" + linea);
                }
            } catch (IOException ex) {
                Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(Lanzador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

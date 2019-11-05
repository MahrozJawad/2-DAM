
package controldestock;


public class Envio extends Thread{
    private int piezasLlegadas;

    @Override
    public void run() {
        this.piezasLlegadas = (int)(Math.random() * ((1000 - 400) + 1)) + 400;
        
        System.out.println("Llegan " + piezasLlegadas +" piezas");
    }

    public int getPiezasLlegadas() {
        return piezasLlegadas;
    }

    public void setPiezasLlegadas(int piezasLlegadas) {
        this.piezasLlegadas = piezasLlegadas;
    }
    
}

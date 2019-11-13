
package ejercicio2;

public class Ejercicio2 {

    public static void main(String[] args) {
        
        Camarero c = new Camarero();
        Jarra j  = new Jarra();
        
        Comensal c1 = new Comensal(1, c, j);
        c.start();
        
        Comensal c2 = new Comensal(2, c, j);
        Comensal c3 = new Comensal(3, c, j);
        
        c1.start();
        c2.start();
        c3.start();
    }
}

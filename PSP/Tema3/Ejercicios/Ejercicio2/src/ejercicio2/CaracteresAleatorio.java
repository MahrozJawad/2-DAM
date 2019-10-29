
package ejercicio2;


public class CaracteresAleatorio extends  Thread{

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println((char)(Math.random()*(122-97)+97));
        }
    }
    
}


package ejercicio1;


public class MultiplosDe10 extends Thread{

    @Override
    public void run() {
        System.out.println("Los Multiplos de 10:");
        for (int i = 0; i < 100; i++) {
            if(i%10==0) {
                System.out.print(i + " ");
            }
        }
        
    }
    
}

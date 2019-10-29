
package ejercicio1;


public class SumaDivisibles extends  Thread{

    @Override
    public void run() {
        int numeroBajo=100;
        int numeroAlto=1000;
        int suma = 0;
        
        for (int i = numeroBajo; i < numeroAlto; i++) {
            if(i%5==0) {
                suma +=i;
            }
        }
        System.out.println("La suma: "+suma);
    }
}

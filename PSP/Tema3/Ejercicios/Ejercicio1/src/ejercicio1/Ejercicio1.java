
package ejercicio1;

public class Ejercicio1 extends Thread{
    public static void main(String[] args) {
        Thread t1 = new Thread(new SumaDivisibles());
        t1.start();
        Thread t2 = new Thread(new SumaDivisibles());
        t2.start();
        Thread t3 = new Thread(new MultiplosDe10());
        t3.start();
    }
}


package banco;

public class Banco {

    public static void main(String[] args) {
        Cuenta c = new Cuenta(2000, 5000);
        
        Persona p1 = new Persona("Juan",c);
        Persona p2 = new Persona("Jose",c);
        Persona p3 = new Persona("Carlos",c);
        
        Thread t1 = new Thread(p1);
        Thread t2 = new Thread(p2);
        Thread t3 = new Thread(p3);
        
        t1.start();
        t2.start();
        t3.start();
        
       
    }

}

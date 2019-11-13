
package examen_psp;

public class Examen_PSP {

    public static void main(String[] args) {
        String[] lista1 = {"dir build", "path"};
        String[] lista2 = {"ver", "ping ad.asd.sad.as"};
        String[] lista3 = {"dasdsdas", "type manifest.mf", "ping www.eldiario.es"};
        
        
        Thread t1 = new Thread(new Lanzador(lista1));
        Thread t2 = new Thread(new Lanzador(lista2));
        Thread t3 = new Thread(new Lanzador(lista3));
        
        t1.start();
        t2.start();
        t3.start();
        
    }
}

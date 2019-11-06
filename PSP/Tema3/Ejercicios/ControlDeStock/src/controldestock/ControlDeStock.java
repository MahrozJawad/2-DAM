
package controldestock;

public class ControlDeStock {

    public static void main(String[] args) throws InterruptedException {
        
        Almacen a = new Almacen();
        
        Retirada r = new Retirada(a);
        Envio e = new Envio(r,a);
        
        Thread retirada = new Thread(r);
        Thread envio = new Thread(e);

        
        retirada.start();
        envio.start();
        
        
    }

}

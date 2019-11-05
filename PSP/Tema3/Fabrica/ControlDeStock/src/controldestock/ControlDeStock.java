
package controldestock;

public class ControlDeStock {

    public static void main(String[] args) throws InterruptedException {
        
        Retirada r = new Retirada();
        Envio e = new Envio();
        
        Thread retirada = new Thread(r);
        Thread envio = new Thread(e);

        
        Almacen almacen = new Almacen();
        
        while (!almacen.error()) {
            almacen.Salida(r.getPedido());
            if(!almacen.error()) {
                retirada.run();
                envio.run();
                almacen.Llegadas(e.getPiezasLlegadas(), r.getStock());
                envio.run();
                almacen.Llegadas(e.getPiezasLlegadas(), r.getStock());
                envio.run();
                almacen.Llegadas(e.getPiezasLlegadas(), r.getStock());
            }
            Thread.sleep(2000);
            
            
            
            
        }
        
        
    }

}

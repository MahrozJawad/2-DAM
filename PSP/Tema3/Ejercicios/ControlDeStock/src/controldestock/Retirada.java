
package controldestock;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Retirada extends Thread{
    private int dia = 0;
    private int stock = 8000;
    private Almacen almacen;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    private int pedido;

    @Override
    public void run() {
        while (!almacen.error()) {
            try {
                System.out.println("<--Dia-->: " + ++dia);
                this.pedido = (int)(Math.random() * ((2500 - 2000) + 1)) + 2000;
                System.out.println("Pedido de " + pedido + " piezas");
                stock = stock - pedido;
                almacen.Salida(stock,pedido);
                if(!almacen.error()) {
                    System.out.println("Hay "+ stock+ " piezas en el almacén");
                }
                
                sleep(2400);
            } catch (InterruptedException ex) {
                Logger.getLogger(Retirada.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public Retirada(Almacen almacen) {
        this.almacen = almacen;
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    
    
    
    
    
    
}

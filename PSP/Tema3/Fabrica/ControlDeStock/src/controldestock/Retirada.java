
package controldestock;

public class Retirada extends Thread{
    private int dia = 0;
    private int stock = 8000;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    private int pedido;

    @Override
    public void run() {
        dia++;
        this.pedido = (int)(Math.random() * ((2500 - 2000) + 1)) + 2000;
        stock = stock - pedido;
        System.out.println(this.toString());
    }
    @Override
    public String toString() {
        
        String cadena = "<--Dia-->:"+dia+"\n"+"Pedido de " + pedido +" piezas\n" + "Hay " + stock +" piezas en el almacén";
        
        return cadena;
    }

    public int getPedido() {
        return pedido;
    }

    public void setPedido(int pedido) {
        this.pedido = pedido;
    }

    
    
    
    
    
    
}


package controldestock;

public class Almacen {
    private int cantidadMaxima = 20000;
    private Boolean error = false;
    private int StockActual=0;
    
    public synchronized void Llegadas(int Piezas, int stock) {
        StockActual  = stock;
        if(StockActual>cantidadMaxima) {
            System.out.println("Se ha superado el Stock");
            error = true;
        }
        else if(!error) {
           StockActual = Piezas + stock;
        }

        
        
    }
    public synchronized void Salida(int stock,int pedido) {
        StockActual  = stock;
        if(pedido>StockActual){
            error = true;
            System.out.println("No hay Piezas Suficiente");
        }
        else if(!error) {
            StockActual  -= pedido;
        }
    }
    
    public boolean error() { 
        return error; 
    }
}

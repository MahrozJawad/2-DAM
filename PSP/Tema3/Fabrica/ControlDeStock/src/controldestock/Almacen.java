
package controldestock;

public class Almacen {
    private int cantidadMaxima = 20000;
    private Boolean error = false;
    private int StockActual=0;
    
    public void Llegadas(int Piezas, int stock) {
        StockActual = Piezas + stock;
        if(StockActual>20000) {
            System.out.println("Se ha superado el Stock");
            error = true;
        }
        else
            System.out.println("Hay " + StockActual +" piezas en el almacén");
        
        
    }
    public void Salida(int piezas) {
        if(StockActual<piezas){
            error = true;
            System.out.println("No hay Piezas Suficiente");
        }
        
    }
    
    public boolean error() { 
        return error; 
    }
}


package banco;

public class Cuenta {
    private double saldoActual;
    private double ingreso;
    private double reintegro;
    private double valorMaximo;
    private boolean error = false;

    public Cuenta(double saldo, double valorMax) {
        saldoActual = saldo;
        valorMaximo = valorMax;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    public double getIngreso() {
        return ingreso;
    }

    public synchronized void setIngreso(double ingreso, Persona p) throws InterruptedException {
        
        if(valorMaximo > (saldoActual+ingreso))
        {
            ImprimeIngreso(p.nombre,ingreso);
            saldoActual += ingreso;
        }
            
        else
        {
            System.out.println("Se ha superado el limite");

            setError(true);
        }
    }

    public double getReintegro() {
        return reintegro;
    }

    public synchronized void setReintegro(double reintegro, Persona p) throws InterruptedException {
        
        if(saldoActual<0) {
            System.out.println("No tienes dinero suficiente.");
            setError(true);
        }
        else 
        {
            Imprimereintegro(p.nombre, reintegro);            
            saldoActual -= reintegro;
        }
    }

    public double getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(double valorMaximo) {
        this.valorMaximo = valorMaximo;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
    
    public void ImprimeIngreso(String nombre, double ingreso) {
        if (!this.isError()) {
            System.out.println(nombre + " ha Ingresado " + ingreso);
            System.out.println("Saldo actual " + Math.round(this.getSaldoActual() * 100) / 100);
        }
    }
    public void Imprimereintegro(String nombre, double reintegro) {
        
        if (!this.isError()) {
            System.out.println(nombre + " ha Reintegrado " + reintegro);
            System.out.println("Saldo actual " + Math.round(this.getSaldoActual() * 100) / 100);
        }
    }
    
    

}

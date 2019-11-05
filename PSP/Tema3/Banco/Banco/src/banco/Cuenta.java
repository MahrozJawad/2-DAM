
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

    public synchronized double getIngreso() {
        return ingreso;
    }

    public synchronized void setIngreso(double ingreso) {
        this.ingreso = ingreso;
        if(valorMaximo > (saldoActual+ingreso))
            saldoActual += this.ingreso;
        else
        {
            if(!isError())
                System.out.println("Se ha superado el limite");
            setError(true);
        }
    }

    public synchronized double getReintegro() {
        return reintegro;
    }

    public synchronized void setReintegro(double reintegro) {
        this.reintegro = reintegro;
        if(saldoActual<0) {
            if(!isError())
                System.out.println("No tienes dinero suficiente.");
            setError(true);
        }
        else 
            saldoActual -= this.reintegro;
        
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
    
    

}

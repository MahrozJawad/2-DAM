
package adivinarnumeros;


public class Jugador extends Thread{

    private Arbitro arbitro;
    private int id;

    public Jugador(int id, Arbitro arbitro) {
        this.arbitro = arbitro;
        this.id = id;
    }
    
    public void run() {
        while (arbitro.Termino() == false) {
            if(arbitro.turnoQueToca() == id){
                int jugada = (int)(10*Math.random()) + 1;
                arbitro.jugada(id, jugada);
            }
        }
    }
    
}

package adivinarnumeros;

public class Arbitro {

    private int numeroAdivinar;
    private int turno;
    private int totalJugadores;
    private boolean finJuego;

    public Arbitro(int nJugadores) {// constructora
        totalJugadores = nJugadores;
        turno = (int)(totalJugadores * Math.random()) + 1;
        numeroAdivinar = (int)(Math.random()*10) + 1;
        finJuego = false;
    }
    public int turnoQueToca() {
        return turno;
    }
    public boolean Termino() {
        return finJuego;
    }
    public synchronized void jugada(int jugador, int numerojugador) {
        if(jugador == turnoQueToca()) {
            System.out.println("Jugador " + jugador + " dice: "+ numerojugador);
            if(numerojugador == numeroAdivinar) {
                System.out.println("Jugador " + jugador+ " gana");
                finJuego = true;
            } else{
                if(turno==totalJugadores)
                    turno = 1;   
                else
                    turno++;
            }
        }
    }
}


package adivinarnumeros;

public class Principal {

    public static void main(String[] args) {

        Arbitro arbitro = new Arbitro(3); // 3 jugadores
        Jugador j1 = new Jugador(1, arbitro);
        Jugador j2 = new Jugador(2, arbitro);
        Jugador j3 = new Jugador(3, arbitro);
        
        
        j1.start();
        j2.start();
        j3.start();
    }
}

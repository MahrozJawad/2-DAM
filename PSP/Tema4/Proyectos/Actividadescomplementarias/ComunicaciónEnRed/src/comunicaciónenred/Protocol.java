package comunicaciónenred;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Protocol {

    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;

    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;

    private String[] clues = {"Turnip", "Little Old Lady", "Atch", "Who", "Who"};
    private String[] answers = {"Turnip the heat, it's cold in here!",
        "I didn't know you could yodel!",
        "Bless you!",
        "Is there an owl in here?",
        "Is there an echo in here?"};
    private int valorAleatorio;

    public String processInput(String theInput) {
        String theOutput = null;
        Scanner e = new Scanner(System.in);

        if (state == WAITING) {
            theOutput = "Elegir El Modo: 1. Modo Normal 2. Modo ConReglas";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            
            switch(theInput) {
                case "1":
                    theOutput = "Hola";
                    break;
                case "Bye.":
                    theOutput = "Hola";
                    break;
                    
            }
        }
        
        return theOutput;
    }

    private boolean AdivinarNumero(int valor) {
        boolean adivinado = false;

        if (valorAleatorio == valor) {
            adivinado = true;
        }

        return adivinado;
    }

    public int getAleatorio(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

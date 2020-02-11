
package examenfinalpsp;
import java.net.*;
import java.io.*;

public class Protocolo {

  private static final int WAITING = 0;
  private static final int NICK = 1;
  private static final int CANTIDAD = 2;
  private static final int APORTAR = 3;
  private static final int FALTA = 4;
  
  
  private static final int terminado = 5;
  
  private int cantidad = Integer.MIN_VALUE;
  private int cantidadMax;

    public Protocolo(int cantidadMax) {
        this.cantidadMax = cantidadMax;
    }
  
  private int state = WAITING;

  public String processInput(String theInput) {
    String theOutput = null;

    if (state == WAITING) {
      theOutput = "Conectado al servidor. Introduzca su Nick.";
      state = NICK;
    } 
    else if (state == NICK) {
        theOutput = theInput;
        state = APORTAR;
    } 
    else if (state == APORTAR) {
        theOutput = "Introduzca la cantidad que desea aportar (0 para fin)";
        state = CANTIDAD;
    }
    else if (state == CANTIDAD) {
         
        while (Integer.parseInt(theInput) > 0) {

            cantidad = Integer.parseInt(theInput);
            theOutput = "Gracias por tu aportación. Con ella ya llevamos " + cantidad;
            state = FALTA;
        }
    }
    else if (state == FALTA) {
        theOutput = "Todavía Faltan " + (cantidadMax - cantidad);
        state = APORTAR;
    }
    
    
    
    if(state == terminado) {
      theOutput = "Fin del Programa.";
      state = WAITING;
    }
      return theOutput;
    }
    
}

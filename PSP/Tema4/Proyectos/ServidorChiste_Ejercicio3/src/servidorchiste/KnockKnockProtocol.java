package servidorchiste;

import java.net.*;
import java.io.*;

public class KnockKnockProtocol {
  private static final int WAITING = 0;
  private static final int SENTKNOCKKNOCK = 1;
  private static final int ANOTHER = 3;
  private static final int UltimoCHISTE = 4;
  private static final int terminado = 5;


  private int state = WAITING;

  public String processInput(String theInput) {
    String theOutput = null;

    if (state == WAITING) {
      theOutput = "¡Me acaba de picar una serpiente!";
      state = SENTKNOCKKNOCK;
    } else if (state == SENTKNOCKKNOCK) {
      if (theInput.equalsIgnoreCase("Cobra?")) {
        theOutput = "¡No, idiota, lo ha hecho gratis! " + "Quieres otro chiste (y/n)";
        state = ANOTHER;
      } else {
        theOutput = "Tienes que decir \"Cobra?\"! " +
		"Intentar de nuevo: ¡Me acaba de picar una serpiente!";
      }
    } else if (state == ANOTHER) {
      if (theInput.equalsIgnoreCase("y")) {
         theOutput = "¿Por qué se suicidó el libro de matemáticas?";
         state = UltimoCHISTE;
      }
      else
          state = terminado;
    } else if (theInput.equalsIgnoreCase("Porque tenia muchos problemas"))
        {
           theOutput = "Perfecto";
           state = terminado;
        }
         else
        {
            theOutput = "Tienes que decir \"Porque tenia muchos problemas\"! " +
                 "Intentar de nuevo: ¿Por qué se suicidó el libro de matemáticas?";
        }
    if(state == terminado) {
      theOutput = "Bye.";
      state = WAITING;
    }
      return theOutput;
    }
}
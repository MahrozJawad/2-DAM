
package ejercicio2;

import java.util.Scanner;


public class Vocales extends Thread{

    @Override
    public void run() {
        Scanner entrada = new Scanner(System.in);
        String vocales="AEIOUaeiou";
        System.out.print("Introduce la cadena: ");
        StringBuilder cadena = new StringBuilder(entrada.nextLine());
        System.out.print("Vocales de la cadena: ");
        
        vocales.toCharArray();
        
        System.out.println(cadena);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio2;

public class Ejercicio2 extends  Thread{
    public static void main(String[] args) {
        Thread t1 = new Thread(new CaracteresAleatorio());
        t1.start();
        Thread t2 = new Thread(new Vocales());
        t2.start();
    }
    
}

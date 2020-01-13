
package swingsuma;

import javax.swing.JFrame;

public class SwingSuma {

    private static JFrame ventana;
public static void main(String[] args) {
java.awt.EventQueue.invokeLater(new Runnable() {
public void run() {
ventana = new SwingSumaClase();
ventana.setLocationRelativeTo(null); // Para centrarla
ventana.setTitle("Suma de dos números");
ventana.setVisible(true);
}
});
}
}


package swingdesplegableapplication;

import java.util.ArrayList;
import javax.swing.JFrame;

public class SwingDesplegableApplication {
    private static JFrame ventana;
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
public void run() {
ArrayList<String> modulos = new ArrayList<>();
modulos.add("Acceso a datos");
modulos.add("Desarrollo de interfaces");
modulos.add("Programación multimedia y dispositivos móviles");
modulos.add("Programación de servicios y procesos");
modulos.add("Sistemas de gestión empresarial");
modulos.add("Empresa e iniciativa emprendedora");
ventana = new SwingDesplegable(modulos);
ventana.setLocationRelativeTo(null); // Para centrarla
ventana.setTitle("Selección");
ventana.setVisible(true);
}
});
        
    }
}

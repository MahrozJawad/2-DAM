package swingtecladomessageapplication;

import javax.swing.JOptionPane;

public class SwingTecladoMessageApplication {

    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Introduce tu nombre");
        JOptionPane.showMessageDialog(null, "Cuidado " + nombre,
                "AVISO", JOptionPane.WARNING_MESSAGE);
        System.out.println(nombre);
    }
}

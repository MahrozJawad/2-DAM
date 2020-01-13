package swingvisual;

import javax.swing.JFrame;

public class SwingVisual {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame ventana = new WinJFrame();
                ventana.setTitle("Cargar visor de archivo de texto:");
                ventana.setVisible(true);
            }
        });
    }
}

package swingdinamico;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class SwingDinamico {

    public static void main(String[] args) throws FileNotFoundException {
        String archivo = JOptionPane.showInputDialog("Introduce el nombre de un archivo: ");
        try {
            PrintWriter fichero = new PrintWriter("./datos/" + archivo + ".txt");
            fichero.println("Creado desde la aplicación de escritorio de tipo Window");
            fichero.close();
            JOptionPane.showMessageDialog(null, "Archivo creado");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error al crear el archivo");
        }
    }
}

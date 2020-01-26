
package ud8ejer801;

import com.dam.gimmongoclass.Cliente;
import com.dam.gimmongoclass.Gimnasio;
import javax.swing.JOptionPane;

public class UD8Ejer801 {

    public static void main(String[] args) {
        String dni = JOptionPane.showInputDialog("Introduce el dni de un cliente: ");
        Cliente c = Gimnasio.obtieneCliente(dni);
        System.out.println(c);
        
    }
}

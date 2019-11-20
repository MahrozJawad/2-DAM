package actividad1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

public class Actividad1 {

    // ----------------------------------------------------------------------------
    //  Crea un objeto URL a partir de la cadena de la url
    // ----------------------------------------------------------------------------
    public static URL creaURL(String url_str) throws MalformedURLException, IOException {
        URL url = null;
        
        try {
            
            url = new URL(url_str);
            
        } catch (MalformedURLException ex) {
            System.err.println("Url no válido: " + ex.getMessage());
        }

        /* url = Crear URL a partir de url_str */
        return url;
    }

    // ----------------------------------------------------------------------------
    //  Lee una URL y nos devuelve su contenido en forma de cadena
    // ----------------------------------------------------------------------------
    public static String leeURL(URL url) {
        InputStream in = null;
        String s = "";
        try {
            
            String linea;
            /*Descomentar para apartado (b) */
            
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.168.0.100", 8080));
            HttpURLConnection connection =
            (HttpURLConnection)new URL(url.toString()).openConnection(proxy);
    
            in = ((HttpURLConnection)new URL(url.toString()).openConnection(proxy)).getInputStream(); // Abre flujo de entrada de URL //;
            BufferedReader r = new BufferedReader(new InputStreamReader(in));// Crear a partir de flujo de entrada in //;
            // Lee de la URL linea a linea

            while ((linea = r.readLine()) != null) {
                s += linea + "\n";
            }
            
        } catch (IOException ex) {
            System.err.println("Error: "  + ex.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                System.err.println("Error al cerrar el stream: " + ex.getMessage());
            }
        }
        /* Descomentar para apartado (b) */
            return s;
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Uso: java Ej1 <url>");
            System.exit(1);
        }

        URL url = creaURL(args[0]);

        String texto = leeURL(url);

        System.out.println(texto);

        System.exit(0);
    }
}

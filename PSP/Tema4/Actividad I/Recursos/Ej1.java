import java.io.*;
import java.net.*;

public class Ej1 {

	// ----------------------------------------------------------------------------
	//  Crea un objeto URL a partir de la cadena de la url
	// ----------------------------------------------------------------------------

	public static URL creaURL(String url_str) {
		URL url = null;

			/* url = Crear URL a partir de url_str */

		return url;
	}

	// ----------------------------------------------------------------------------
	//  Lee una URL y nos devuelve su contenido en forma de cadena
	// ----------------------------------------------------------------------------

	public static String leeURL(URL url) {
		String s = "";
		String linea;

/* Descomentar para apartado (b) *

		try {
			InputStream in = // Abre flujo de entrada de URL //;

			BufferedReader r = // Crear a partir de flujo de entrada in //;

			// Lee de la URL linea a linea

			while( (linea=r.readLine()) != null )
			{
				s += linea + "\n";
			}
		} catch(IOException e) {
			System.err.println("Error leyendo URL");
			System.exit(1);
		}

 * Descomentar para apartado (b) */

		return s;
	}

	public static void main(String [] args) {
		if(args.length != 1) {
			System.err.println("Uso: java Ej1 <url>");
			System.exit(1);
		}

		URL url = creaURL(args[0]);

		String texto = leeURL(url);

		System.out.println(texto);

		System.exit(0);
	}
}
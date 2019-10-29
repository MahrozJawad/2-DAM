public class B02ejer06CopiarFichero {
public static void main(String args[]) {
String texto;
int entero;
String fileName = "C:/tmp/datosbinarios.dat" ;
try {
fuente = new FileInputStream(args[0]);
destino = new FileOutputStream(args[1],true);
int i = fuente.read();
while (i != -1) { // mientras not EOF
destino.write(i);
i = fuente.read();
}
fuente.close();
destino.close();
System.out.println("String: " + texto);
System.out.println("Int: " + entero);
} catch (IOException e) {
System.out.print(e.getMessage());
}
}
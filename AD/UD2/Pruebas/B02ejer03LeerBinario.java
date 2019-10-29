public class B02ejer03LeerBinario {
public static void main(String args[]) {
String texto;
int entero;
String fileName = "C:/tmp/datosbinarios.dat" ;
try {
FileInputStream foStream = new FileInputStream(fileName);
DataInputStream doStream = new DataInputStream (foStream);
texto = doStream.readUTF();
entero = doStream.readInt();
doStream.close();
System.out.println("String: " + texto);
System.out.println("Int: " + entero);
} catch (IOException e) {
System.out.print(e.getMessage());
}
}
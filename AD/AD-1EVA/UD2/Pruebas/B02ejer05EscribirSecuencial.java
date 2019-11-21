public class B02ejer05EscribirSecuencial {
public static void main(String args[]) {
String texto;
int entero;
String fileName = "C:/tmp/datosbinarios.dat" ;
try {
String strFichero = "C:/tmp/secuencial.dat";
int edad = 32 ;
archivo = new DataOutputStream( new FileOutputStream(strFichero, true) );
archivo.writeUTF( "Nombre" );
archivo.writeUTF( "Apellidos" );
archivo.writeInt(edad) ;
archivo.close();
System.out.println("String: " + texto);
System.out.println("Int: " + entero);
} catch (IOException e) {
System.out.print(e.getMessage());
}
}
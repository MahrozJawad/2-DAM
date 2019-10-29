public class B02ejer03EscribirBinario {
public static void main(String args[]) {
String texto = "Esto es un texto para almacenarlo\n
en el archivo binario.\n";
String fileName = "C:/tmp/datosbinarios.dat" ;
try {
FileOutputStream foStream = new FileOutputStream(fileName);
DataOutputStream doStream = new DataOutputStream (foStream);
doStream.writeUTF(texto);
doStream.writeInt(5);
doStream.close();
} catch (IOException e) {
System.out.print(e.getMessage());
}
}
}
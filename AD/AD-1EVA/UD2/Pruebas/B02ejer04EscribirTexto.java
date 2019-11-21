public class B02ejer03LeerBinario {
public static void main(String args[]) {
String texto;
int entero;
String fileName = "C:/tmp/datosbinarios.dat" ;
try {
archivo = new File ("C:/tmp/archivotexto.txt");
fw = new FileWriter (archivo);
bw = new BufferedWriter(fw);
bw.write("Este es un fichero de texto");
bw.newLine();
bw.write("que contiene varias líneas\n");
bw.write("y se crea con BufferedWriter.\n");
bw.flush();
bw.close();
fw.close();
System.out.println("String: " + texto);
System.out.println("Int: " + entero);
} catch (IOException e) {
System.out.print(e.getMessage());
}
}
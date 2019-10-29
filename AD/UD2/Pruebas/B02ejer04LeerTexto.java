public class B02ejer03LeerBinario {
public static void main(String args[]) {
String texto;
int entero;
String fileName = "C:/tmp/datosbinarios.dat" ;
try {
archivo = new File ("C:/tmp/archivotexto.txt");
fr = new FileReader(archivo);
br = new BufferedReader(fr);
String linea;
while((linea=br.readLine())!=null){
System.out.println(linea);
}
br.close();
fr.close();
System.out.println("String: " + texto);
System.out.println("Int: " + entero);
} catch (IOException e) {
System.out.print(e.getMessage());
}
}
public class B0ejer02Filtrar implements FilenameFilter {
String extension;
B0ejer02Filtrar(String extension){
this.extension = extension;
}
public boolean accept(File dir, String name){
return name.endsWith(extension);
}
public static void main(String[] args) {
try {
File fichero=new File("c:/windows/.");
String[] listadeArchivos = fichero.list();
listadeArchivos = fichero.list(new B0ejer02Filtrar(".exe"));
static String substFileSeparator(String ruta) {
String separador ="\\";
try {
if File.separator.equals(separador) ) {
separador = "/";
}
return ruta.replaceAll(separador, File.separator);
} catch (Exception e) {
// Por si ocurre una java.util.regex.PatternSyntaxException
return ruta.replaceAll(separador+separador, File.separator);
}
}
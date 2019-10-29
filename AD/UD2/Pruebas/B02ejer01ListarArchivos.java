import java.io.File;

public class B02ejer01ListarArchivos {
   public static void main(String [] args)
    {
      String carpeta="C:/Windows";
      File ruta = new File(carpeta);
      if(ruta.exists()) 
      {
         String[] listaArchivos = ruta.list();
         for(int i=0; i<listaArchivos.length; i++)
         {
            System.out.println(listaArchivos[i]);
         }
      }
   }
}
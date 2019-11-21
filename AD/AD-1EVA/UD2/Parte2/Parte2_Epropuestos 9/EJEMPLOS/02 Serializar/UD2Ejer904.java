import java.util.Scanner;
import java.io.Serializable;
import java.util.InputMismatchException;

// ***************************************
// Persona
// ***************************************
class Persona implements Serializable { 
	private String nombre; 
	private String email; 
    private int year; 
	
	// Constructores 
	public Persona(){
	}
	
	// Constructor 
	public Persona(String pNombre, String pEmail, int pYear) { 
	  nombre   = pNombre; 
      email    = pEmail;
      year     = pYear;
	} 
	
	// Método adicional 
	@Overrride
	public void toString() { 
		return nombre + ":" + email + ":" + year; 
	}
} 

// ***************************************
// UtilString
// ***************************************
class UtilString {
    
    public static String StrRepetir(char c, int n) {
        String resul="";
        for (int x=1; x<=n; x++) {
            resul = resul + c;
        }
        return resul;
    }
    
    
    public static void Linea() {
        System.out.println(StrRepetir('-',40));
    }    
}

// ***************************************
// Menu
// ***************************************
class Menu {

    public static void Mostrar() {
        
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario    
        
        while (!salir) {
            UtilString.Linea();
            System.out.println("1. Añadir datos de persona");
            System.out.println("2. Mostrar todas las personas");
            System.out.println("3. Buscar persona por nombre");
            System.out.println(UtilString.StrRepetir('-',20));
            System.out.println("0. Salir");
            System.out.println(UtilString.StrRepetir('-',20));

            try {
                
                System.out.print("Escribe una de las opciones: ");
                opcion = sn.nextInt();

                UtilString.Linea();
                switch (opcion) {
                    case 1:
                        UD2Ejer904Opciones.AddPersona();
                        break;  
                    case 2:
                        UD2Ejer904Opciones.MostrarPersonas();
                        break;  
                    case 3:
                        UD2Ejer904Opciones.BuscaPersona();
                        break;  
                        
                    // **************************
                    // SALIR
                    // **************************
                    case 0:
                        salir = true;
                        System.out.println("Terminado");
                        UtilString.Linea();
                        break;                        
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
        } catch (InputMismatchException e) {
                UtilString.Linea();
                System.out.println("Debe insertar un número");
                sn.next();
            }
        }               
              
    }
    
    
    
    
}


class UD2Ejer904Opciones { 
  
   public static void AddPersona() {
      System.out.println("Opción 1");
   }
   
   public static void BuscaPersona() {
      System.out.println("Opción 2");
   }

   public static void MostrarPersonas() {
      System.out.println("Opción 3");
   }
}


public class UD2Ejer904 { 
	public static void main( String[] args ) { 
   
      Menu.Mostrar();
   
	}
 
}
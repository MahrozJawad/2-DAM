/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ud2ejer904;

/**
 *
 * @author Mahroz
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
	
	// Constructor 
	public Persona(String pNombre, String pEmail, int pYear) { 
	  nombre   = pNombre; 
      email    = pEmail;
      year     = pYear;
	} 
	
	// Método adicional
        @Override
	public String toString() {
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


class  UD2Ejer904Opciones { 
  
    private static final File FICHERO = new File("./datos/personas.dat");
    //Input para leer
    private static FileInputStream ficheroInput;
    private static ObjectInputStream ficheroObjectInput;
    
    //output para escribir
    private static FileOutputStream ficheroOutput;
    private static ObjectOutputStream ficheroObjectOutput;
    private static ArrayList<Persona> personas = new ArrayList<>();
    private static Scanner entrada = new Scanner(System.in);
    
   public static void AddPersona() {
      
       try {
            if (FICHERO.length() > 0) {
               //Proceso de carga.
                personas = new ArrayList<>();
                ficheroInput = new FileInputStream(FICHERO);
                ficheroObjectInput = new ObjectInputStream(ficheroInput);
                while (ficheroInput.available() > 0) {                
                    personas.add((Persona)ficheroObjectInput.readObject());
                }
                ficheroObjectInput.close();
           }
            
            //proceso de Añadir más personas en el array
            
            System.out.print("Introduce Nombre de la persona: ");
            String nombre = entrada.nextLine();
            System.out.print("Introduce Email de la persona: ");
            String email = entrada.nextLine();
            System.out.print("Introduce año de Nacimiento de la persona: ");
            int año = entrada.nextInt();
            personas.add(new Persona(nombre, email, año));
            
            //Proceso para guardar el fichero.
            ficheroOutput = new FileOutputStream(FICHERO);
            ficheroObjectOutput = new ObjectOutputStream(ficheroOutput);
            for (int i = 0; i < personas.size(); i++) {
                ficheroObjectOutput.writeObject(personas.get(i));
            }
            ficheroObjectOutput.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UD2Ejer904.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UD2Ejer904.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UD2Ejer904.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
   
   public static void BuscaPersona() {
      
       if (FICHERO.length() > 0) {
            System.out.print("Introduce la palabra para buscar: ");
            String palabra = entrada.nextLine();

            for (int i = 0; i < personas.size(); i++) {
                if (personas.get(i).toString().contains(palabra)) {
                     System.out.println(personas.get(i).toString());
                 }
            }
       }
       else
           System.err.println("No hay datos para mostrar");
   }
   public static void MostrarPersonas() {
       
       if (FICHERO.length() > 0) {
            ficheroInput = null;
             Persona p = null;
             try {
                 ficheroInput = new FileInputStream(FICHERO);
                 ficheroObjectInput = new ObjectInputStream(ficheroInput);

                 while(ficheroInput.available() > 0) {
                     p = (Persona)ficheroObjectInput.readObject();
                     System.out.println(p.toString());
                 }


             } catch (FileNotFoundException ex) {
                 Logger.getLogger(UD2Ejer904.class.getName()).log(Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(UD2Ejer904.class.getName()).log(Level.SEVERE, null, ex);
             } catch (ClassNotFoundException ex) {
                 Logger.getLogger(UD2Ejer904.class.getName()).log(Level.SEVERE, null, ex);
             } finally {
                 try {
                     ficheroInput.close();
                 } catch (IOException ex) {
                     Logger.getLogger(UD2Ejer904.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
       }
       else
           System.err.println("No hay datos para buscar");
   }
}

public class UD2Ejer904 { 
	public static void main( String[] args ) { 
   
      Menu.Mostrar();
   
	}
 
}

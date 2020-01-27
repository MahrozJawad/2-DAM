
package clases;

import java.io.Serializable;
import java.text.Collator;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="fruta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder={"nombre"})
public class Frutas implements Serializable {
    @XmlElement(name="nombre") String nombre;

    public Frutas() {
    }

    public Frutas(String nombre) {
        this.nombre = nombre;
    }
    

    public String getFruta() {
        return nombre;
    }

    public void setFruta(String nombre) {
        this.nombre = nombre;
    }
    

    @Override
    public String toString() {
        return "{\"fruta\":\"" + nombre + "\"}";
    }
    
    @Override
    public boolean equals(Object o) { 
  
        // If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Frutas)) { 
            return false; 
        } 
          
        // typecast o to Complex so that we can compare data members  
        Frutas c = (Frutas) o; 
          
        // Compare the data members and return accordingly  
        //return this.getFruta().equals(c.getFruta()); 
        Collator collator = Collator.getInstance();
        collator.setStrength(Collator.PRIMARY);
        System.out.println(this.getFruta()+"-"+c.getFruta());
        return  (collator.compare(this.getFruta(),c.getFruta()) == 0 );
    }     
    
    
}

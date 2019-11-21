
package ud2ejercsv1401toxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="contenedor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"idContenedor","producto","pais","ciudad"})
public class Contenedor {
    @XmlElement(name = "id")
    private String idContenedor;
    @XmlElement(name = "producto")
    private String producto;
    @XmlElement(name = "pais")
    private String pais;
    @XmlElement(name = "ciudad")
    private String ciudad;

    public Contenedor() {
    }

    public Contenedor(String idContenedor, String producto, String pais, String Ciudad) {
        this.idContenedor = idContenedor;
        this.producto = producto;
        this.pais = pais;
        this.ciudad = Ciudad;
    }

    public String getIdContenedor() {
        return idContenedor;
    }

    public void setIdContenedor(String idContenedor) {
        this.idContenedor = idContenedor;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.ciudad = Ciudad;
    }
    
    
    
}

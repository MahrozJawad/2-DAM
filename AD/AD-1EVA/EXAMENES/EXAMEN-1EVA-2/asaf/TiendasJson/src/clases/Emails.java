
package clases;

import java.io.Serializable;


public class Emails implements Serializable {
    private String idtienda;
    private String email; 

    public Emails() {
    }

    public Emails(String idtienda, String email) {
          this.idtienda = idtienda;
        this.email = email;
    }

    public String getIdtienda() {
        return idtienda;
    }

    public void setIdtienda(String idtienda) {
        this.idtienda = idtienda;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Emails{idtienda=" + idtienda + ", email=" + email + '}';
    }
    
    
    
    
}


package ud2json02;


public class Objeto {
    int idObjeto;
    String nomObjeto;
    public Objeto(int idObjeto, String nomObjeto) {
        this.idObjeto = idObjeto;
        this.nomObjeto = nomObjeto;
    }
    public int getIdObjeto() {
        return idObjeto;
    }
    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }
    public String getNomObjeto() {
        return nomObjeto;
    }
    public void setNomObjeto(String nomObjeto) {
        this.nomObjeto = nomObjeto;
    }
    @Override
    public String toString() {
        return "\n{" + "id=" + idObjeto + ", Nombre=" + nomObjeto + '}';
    }
}

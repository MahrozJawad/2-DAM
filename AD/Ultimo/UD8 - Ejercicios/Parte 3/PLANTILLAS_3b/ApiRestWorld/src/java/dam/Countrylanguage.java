
package dam;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "countrylanguage")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Countrylanguage.findAll", query = "SELECT c FROM Countrylanguage c")
    , @NamedQuery(name = "Countrylanguage.findByCountrycode", query = "SELECT c FROM Countrylanguage c WHERE c.countrylanguagePK.countrycode = :countrycode")
    , @NamedQuery(name = "Countrylanguage.findByLanguagename", query = "SELECT c FROM Countrylanguage c WHERE c.countrylanguagePK.languagename = :languagename")
    , @NamedQuery(name = "Countrylanguage.findByIsofficial", query = "SELECT c FROM Countrylanguage c WHERE c.isofficial = :isofficial")
    , @NamedQuery(name = "Countrylanguage.findByPercentage", query = "SELECT c FROM Countrylanguage c WHERE c.percentage = :percentage")})
public class Countrylanguage implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CountrylanguagePK countrylanguagePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "isofficial")
    private String isofficial;
    @Basic(optional = false)
    @NotNull
    @Column(name = "percentage")
    private float percentage;
    @JoinColumn(name = "countrycode", referencedColumnName = "code", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Country country;

    public Countrylanguage() {
    }

    public Countrylanguage(CountrylanguagePK countrylanguagePK) {
        this.countrylanguagePK = countrylanguagePK;
    }

    public Countrylanguage(CountrylanguagePK countrylanguagePK, String isofficial, float percentage) {
        this.countrylanguagePK = countrylanguagePK;
        this.isofficial = isofficial;
        this.percentage = percentage;
    }

    public Countrylanguage(String countrycode, String languagename) {
        this.countrylanguagePK = new CountrylanguagePK(countrycode, languagename);
    }

    public CountrylanguagePK getCountrylanguagePK() {
        return countrylanguagePK;
    }

    public void setCountrylanguagePK(CountrylanguagePK countrylanguagePK) {
        this.countrylanguagePK = countrylanguagePK;
    }

    public String getIsofficial() {
        return isofficial;
    }

    public void setIsofficial(String isofficial) {
        this.isofficial = isofficial;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countrylanguagePK != null ? countrylanguagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Countrylanguage)) {
            return false;
        }
        Countrylanguage other = (Countrylanguage) object;
        if ((this.countrylanguagePK == null && other.countrylanguagePK != null) || (this.countrylanguagePK != null && !this.countrylanguagePK.equals(other.countrylanguagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dam.Countrylanguage[ countrylanguagePK=" + countrylanguagePK + " ]";
    }

}

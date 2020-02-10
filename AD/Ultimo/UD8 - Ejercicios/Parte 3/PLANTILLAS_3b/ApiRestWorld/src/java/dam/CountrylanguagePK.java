
package dam;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Embeddable
public class CountrylanguagePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "countrycode")
    private String countrycode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "languagename")
    private String languagename;

    public CountrylanguagePK() {
    }

    public CountrylanguagePK(String countrycode, String languagename) {
        this.countrycode = countrycode;
        this.languagename = languagename;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getLanguagename() {
        return languagename;
    }

    public void setLanguagename(String languagename) {
        this.languagename = languagename;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (countrycode != null ? countrycode.hashCode() : 0);
        hash += (languagename != null ? languagename.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CountrylanguagePK)) {
            return false;
        }
        CountrylanguagePK other = (CountrylanguagePK) object;
        if ((this.countrycode == null && other.countrycode != null) || (this.countrycode != null && !this.countrycode.equals(other.countrycode))) {
            return false;
        }
        if ((this.languagename == null && other.languagename != null) || (this.languagename != null && !this.languagename.equals(other.languagename))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dam.CountrylanguagePK[ countrycode=" + countrycode + ", languagename=" + languagename + " ]";
    }

}

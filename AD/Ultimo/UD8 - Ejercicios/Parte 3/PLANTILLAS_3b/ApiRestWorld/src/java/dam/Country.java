
package dam;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "country")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Country.findAll", query = "SELECT c FROM Country c")
    , @NamedQuery(name = "Country.findByCode", query = "SELECT c FROM Country c WHERE c.code = :code")
    , @NamedQuery(name = "Country.findByName", query = "SELECT c FROM Country c WHERE c.name = :name")
    , @NamedQuery(name = "Country.findByContinent", query = "SELECT c FROM Country c WHERE c.continent = :continent")
    , @NamedQuery(name = "Country.findByRegion", query = "SELECT c FROM Country c WHERE c.region = :region")
    , @NamedQuery(name = "Country.findBySurfacearea", query = "SELECT c FROM Country c WHERE c.surfacearea = :surfacearea")
    , @NamedQuery(name = "Country.findByIndepyear", query = "SELECT c FROM Country c WHERE c.indepyear = :indepyear")
    , @NamedQuery(name = "Country.findByPopulation", query = "SELECT c FROM Country c WHERE c.population = :population")
    , @NamedQuery(name = "Country.findByLifeexpectancy", query = "SELECT c FROM Country c WHERE c.lifeexpectancy = :lifeexpectancy")
    , @NamedQuery(name = "Country.findByGnp", query = "SELECT c FROM Country c WHERE c.gnp = :gnp")
    , @NamedQuery(name = "Country.findByGnpold", query = "SELECT c FROM Country c WHERE c.gnpold = :gnpold")
    , @NamedQuery(name = "Country.findByLocalname", query = "SELECT c FROM Country c WHERE c.localname = :localname")
    , @NamedQuery(name = "Country.findByGovernmentform", query = "SELECT c FROM Country c WHERE c.governmentform = :governmentform")
    , @NamedQuery(name = "Country.findByHeadofstate", query = "SELECT c FROM Country c WHERE c.headofstate = :headofstate")
    , @NamedQuery(name = "Country.findByCapital", query = "SELECT c FROM Country c WHERE c.capital = :capital")
    , @NamedQuery(name = "Country.findByCode2", query = "SELECT c FROM Country c WHERE c.code2 = :code2")})
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 52)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "continent")
    private String continent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 26)
    @Column(name = "region")
    private String region;
    @Basic(optional = false)
    @NotNull
    @Column(name = "surfacearea")
    private float surfacearea;
    @Column(name = "indepyear")
    private Short indepyear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "population")
    private int population;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "lifeexpectancy")
    private Float lifeexpectancy;
    @Column(name = "gnp")
    private Float gnp;
    @Column(name = "gnpold")
    private Float gnpold;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "localname")
    private String localname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "governmentform")
    private String governmentform;
    @Size(max = 60)
    @Column(name = "headofstate")
    private String headofstate;
    @Column(name = "capital")
    private Integer capital;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "code2")
    private String code2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "countrycode")
    private Collection<City> cityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private Collection<Countrylanguage> countrylanguageCollection;

    public Country() {
    }

    public Country(String code) {
        this.code = code;
    }

    public Country(String code, String name, String continent, String region, float surfacearea, int population, String localname, String governmentform, String code2) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfacearea = surfacearea;
        this.population = population;
        this.localname = localname;
        this.governmentform = governmentform;
        this.code2 = code2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public float getSurfacearea() {
        return surfacearea;
    }

    public void setSurfacearea(float surfacearea) {
        this.surfacearea = surfacearea;
    }

    public Short getIndepyear() {
        return indepyear;
    }

    public void setIndepyear(Short indepyear) {
        this.indepyear = indepyear;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public Float getLifeexpectancy() {
        return lifeexpectancy;
    }

    public void setLifeexpectancy(Float lifeexpectancy) {
        this.lifeexpectancy = lifeexpectancy;
    }

    public Float getGnp() {
        return gnp;
    }

    public void setGnp(Float gnp) {
        this.gnp = gnp;
    }

    public Float getGnpold() {
        return gnpold;
    }

    public void setGnpold(Float gnpold) {
        this.gnpold = gnpold;
    }

    public String getLocalname() {
        return localname;
    }

    public void setLocalname(String localname) {
        this.localname = localname;
    }

    public String getGovernmentform() {
        return governmentform;
    }

    public void setGovernmentform(String governmentform) {
        this.governmentform = governmentform;
    }

    public String getHeadofstate() {
        return headofstate;
    }

    public void setHeadofstate(String headofstate) {
        this.headofstate = headofstate;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    @XmlTransient
    public Collection<City> getCityCollection() {
        return cityCollection;
    }

    public void setCityCollection(Collection<City> cityCollection) {
        this.cityCollection = cityCollection;
    }

    @XmlTransient
    public Collection<Countrylanguage> getCountrylanguageCollection() {
        return countrylanguageCollection;
    }

    public void setCountrylanguageCollection(Collection<Countrylanguage> countrylanguageCollection) {
        this.countrylanguageCollection = countrylanguageCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dam.Country[ code=" + code + " ]";
    }

}

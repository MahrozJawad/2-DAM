
package clases;

import java.io.Serializable;

public class City implements Serializable{
    
    private int id;
    private String name;
    private String district;
    private String population;
    private String countrycode;

    public City(int id, String name, String district, String population, String countrycode) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.population = population;
        this.countrycode = countrycode;
    }

    public City() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name=" + name + ", district=" + district + ", population=" + population + ", countrycode=" + countrycode + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
    
    

}

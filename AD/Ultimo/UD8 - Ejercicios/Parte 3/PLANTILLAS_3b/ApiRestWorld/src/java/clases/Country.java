
package clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Country implements Serializable{
    
    private String code;
    private String name;
    private String continent;
    private double surfacearea;
    private City capital;
    private ArrayList<City> cities;

    public Country(String code, String name, String continent, double surfacearea, City capital, ArrayList<City> cities) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.surfacearea = surfacearea;
        this.capital = capital;
        this.cities = cities;
    }

    public Country() {
    }

    public Country(String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Country{" + "code=" + code + ", name=" + name + ", continent=" + continent + ", surfacearea=" + surfacearea + ", capital=" + capital + ", cities=" + cities + '}';
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

    public double getSurfacearea() {
        return surfacearea;
    }

    public void setSurfacearea(double surfacearea) {
        this.surfacearea = surfacearea;
    }

    public City getCapital() {
        return capital;
    }

    public void setCapital(City capital) {
        this.capital = capital;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    
    
}

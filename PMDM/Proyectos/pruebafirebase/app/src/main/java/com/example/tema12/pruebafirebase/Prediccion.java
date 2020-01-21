package com.example.tema12.pruebafirebase;

import java.io.Serializable;

public class Prediccion implements Serializable {
    private String cielo;
    private String temperatura;
    private String humedad;

    public Prediccion(String cielo, String temperatura, String humedad) {
        this.cielo = cielo;
        this.temperatura = temperatura;
        this.humedad = humedad;
    }

    public String getCielo() {
        return cielo;
    }

    public void setCielo(String cielo) {
        this.cielo = cielo;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getHumedad() {
        return humedad;
    }

    public void setHumedad(String humedad) {
        this.humedad = humedad;
    }
}

package com.accesodatos.springboot_joboffers.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Direccion {

    private String pais;
    private String region;
    private String ciudad;

    //CONSTRUCTORES
    public Direccion() {
    }

    public Direccion(String pais, String region, String ciudad) {
        this.pais = pais;
        this.region = region;
        this.ciudad = ciudad;
    }

    //GETTERS Y SETTERS
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}

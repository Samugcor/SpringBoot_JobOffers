package com.accesodatos.springboot_joboffers.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "empresas")
@PrimaryKeyJoinColumn(name = "idUsuario")
@DiscriminatorValue(value = "0")
public class Empresa extends Usuario {

    @Column(nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "empresa")
    private List<Oferta> ofertasPublicadas;

    //CONSTRUCTORES
    public Empresa() {

    }

    public Empresa(String nombre, String telefono, String email, Direccion direccion, String descripcion, List<Oferta> ofertasPublicadas) {
        super(nombre, telefono, email, direccion);
        this.descripcion = descripcion;
        this.ofertasPublicadas = ofertasPublicadas;
    }

    //GETTERS Y SETTERS
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Oferta> getOfertasPublicadas() {
        return ofertasPublicadas;
    }

    public void setOfertasPublicadas(List<Oferta> ofertasPublicadas) {
        this.ofertasPublicadas = ofertasPublicadas;
    }

    public void addOferta(Oferta oferta) {
        this.ofertasPublicadas.add(oferta);
        oferta.setEmpresa(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Empresa empresa)) return false;
        return Objects.equals(descripcion, empresa.descripcion) && Objects.equals(ofertasPublicadas, empresa.ofertasPublicadas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descripcion, ofertasPublicadas);
    }
}

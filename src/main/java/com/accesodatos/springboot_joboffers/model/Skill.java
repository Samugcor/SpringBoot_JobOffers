package com.accesodatos.springboot_joboffers.model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skills")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSkill;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String descripcion;

    @ManyToMany(mappedBy = "listaSkills")
    private List<Candidato> candidatos;

    @ManyToMany(mappedBy = "listaSkills")
    private List<Oferta> ofertas;

    //CONSTRUCTORES
    public Skill() {
    }

    public Skill(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.candidatos = new ArrayList<Candidato>();
        this.ofertas = new ArrayList<Oferta>();
    }

    public Skill(String nombre, String descripcion, List<Candidato> candidatos, List<Oferta> ofertas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.candidatos = candidatos;
        this.ofertas = ofertas;
    }

    //GETTERS Y SETTERS

    public long getIdSkill() {
        return idSkill;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Candidato> getCandidatos() {
        return candidatos;
    }

    public void addCandidatos(Candidato candidato) {
        this.candidatos.add(candidato);
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void addOferta(Oferta oferta) {
        this.ofertas.add(oferta);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Skill skill)) return false;
        return idSkill == skill.idSkill && Objects.equals(nombre, skill.nombre) && Objects.equals(descripcion, skill.descripcion) && Objects.equals(candidatos, skill.candidatos) && Objects.equals(ofertas, skill.ofertas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSkill, nombre, descripcion, candidatos, ofertas);
    }
}

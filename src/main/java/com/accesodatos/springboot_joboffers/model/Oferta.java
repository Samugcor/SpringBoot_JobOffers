package com.accesodatos.springboot_joboffers.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "ofertas")
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOferta;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int sueldo;

    @Column(nullable = false)
    private LocalDate fechaPublicacion;

    @ManyToOne(optional = false)
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;

    @ManyToMany
    @JoinTable(name = "oferta_skills",joinColumns = @JoinColumn(name = "idOferta"),inverseJoinColumns = @JoinColumn(name = "idSkill"))
    private List<Skill> listaSkills;

    @ManyToMany(mappedBy = "ofertas")
    private List<Candidato> listaCandidatos;

    //CONSTRUCTORES
    public Oferta() {
    }

    public Oferta(String nombre, String descripcion, int sueldo, LocalDate fechaPublicacion, Empresa empresa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sueldo = sueldo;
        this.fechaPublicacion = fechaPublicacion;
        this.empresa = empresa;
        this.listaCandidatos = new ArrayList<Candidato>();
    }

    public Oferta(String nombre, String descripcion, int sueldo, LocalDate fechaPublicacion, Empresa empresa, List<Candidato> listaCandidatos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.sueldo = sueldo;
        this.fechaPublicacion = fechaPublicacion;
        this.empresa = empresa;
        this.listaCandidatos = listaCandidatos;
    }

    //GETTERS Y SETTERS
    public long getIdOferta() {
        return idOferta;
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

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Candidato> getListaCandidatos() {
        return listaCandidatos;
    }

    public void addCandidato(Candidato candidato) {
        if(!this.listaCandidatos.contains(candidato)) {
            listaCandidatos.add(candidato);
            candidato.addOferta(this);
        }
    }

    public List<Skill> getListaSkills() {
        return listaSkills;
    }

    public void addSkill(Skill skill) {
        if (!this.listaSkills.contains(skill)) {
            listaSkills.add(skill);
            skill.addOferta(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Oferta oferta)) return false;
        return idOferta == oferta.idOferta && sueldo == oferta.sueldo && Objects.equals(nombre, oferta.nombre) && Objects.equals(descripcion, oferta.descripcion) && Objects.equals(fechaPublicacion, oferta.fechaPublicacion) && Objects.equals(empresa, oferta.empresa) && Objects.equals(listaSkills, oferta.listaSkills) && Objects.equals(listaCandidatos, oferta.listaCandidatos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOferta, nombre, descripcion, sueldo, fechaPublicacion, empresa, listaSkills, listaCandidatos);
    }
}

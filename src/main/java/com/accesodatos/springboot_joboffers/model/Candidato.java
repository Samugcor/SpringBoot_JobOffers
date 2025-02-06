package com.accesodatos.springboot_joboffers.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "candidatos")
@PrimaryKeyJoinColumn(name = "idUsuario")
@DiscriminatorValue(value = "1")
public class Candidato extends Usuario{

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false)
    private int edad;

    @ManyToMany
    @JoinTable(name = "candidatos_skills", joinColumns = @JoinColumn(name = "idUsuario"),inverseJoinColumns = @JoinColumn(name = "idSkill"))
    private List<Skill> listaSkills;

    @ManyToMany
    @JoinTable(name = "candidatos_ofertas", joinColumns = @JoinColumn(name = "idUsuario"),inverseJoinColumns = @JoinColumn(name = "idOferta"))
    private List<Oferta> ofertas;

    //CONSTRUCTORES
    public Candidato() {

    }

    public Candidato(String nombre, String telefono, String email, Direccion direccion, String apellidos, int edad, List<Oferta> ofertas) {
        super(nombre, telefono, email, direccion);
        this.apellidos = apellidos;
        this.edad = edad;
        this.ofertas = ofertas;
    }

    //GETTERS Y SETTERS
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public void addOferta(Oferta oferta) {
        if(!this.ofertas.contains(oferta)) {
            this.ofertas.add(oferta);
            oferta.addCandidato(this);
        }
    }

    public List<Skill> getListaSkills() {
        return listaSkills;
    }

    public void addListaSkill(Skill skill) {
        if(!this.listaSkills.contains(skill)) {
            this.listaSkills.add(skill);
            skill.addCandidatos(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Candidato candidato)) return false;
        return edad == candidato.edad && Objects.equals(apellidos, candidato.apellidos) && Objects.equals(listaSkills, candidato.listaSkills) && Objects.equals(ofertas, candidato.ofertas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(apellidos, edad, listaSkills, ofertas);
    }
}

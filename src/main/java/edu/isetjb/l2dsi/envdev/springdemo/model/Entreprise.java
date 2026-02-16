package edu.isetjb.l2dsi.envdev.springdemo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ENTREPRISE")
public class Entreprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    
    private String adresse;

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Departement> departements = new ArrayList<>();

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employe> employes = new ArrayList<>();

    public Entreprise() {
    }

    public Entreprise(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
}
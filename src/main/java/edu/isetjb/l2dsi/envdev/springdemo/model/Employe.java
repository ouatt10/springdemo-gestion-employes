package edu.isetjb.l2dsi.envdev.springdemo.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYES")
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer matricule;

    private String nom;
    
    private String prenom;
    
    private String fonction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entreprise_id", nullable = false)
    @JsonIgnoreProperties({"employes", "departements"})
    private Entreprise entreprise;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "employe_departement",
        joinColumns = @JoinColumn(name = "employe_id"),
        inverseJoinColumns = @JoinColumn(name = "departement_id")
    )
    @JsonIgnoreProperties({"employes", "entreprise"})
    private List<Departement> departements = new ArrayList<>();

    public Employe() {
    }

    public Employe(String nom, String prenom, String fonction) {
        this.nom = nom;
        this.prenom = prenom;
        this.fonction = fonction;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public List<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(List<Departement> departements) {
        this.departements = departements;
    }
}
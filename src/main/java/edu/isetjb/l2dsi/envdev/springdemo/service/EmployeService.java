package edu.isetjb.l2dsi.envdev.springdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.isetjb.l2dsi.envdev.springdemo.model.Departement;
import edu.isetjb.l2dsi.envdev.springdemo.model.Employe;
import edu.isetjb.l2dsi.envdev.springdemo.model.Entreprise;
import edu.isetjb.l2dsi.envdev.springdemo.repository.DepartementRepository;
import edu.isetjb.l2dsi.envdev.springdemo.repository.EmployeRepository;
import edu.isetjb.l2dsi.envdev.springdemo.repository.EntrepriseRepository;

@Service
public class EmployeService {

    private final EmployeRepository employeRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final DepartementRepository departementRepository;

    public EmployeService(EmployeRepository employeRepository, 
                         EntrepriseRepository entrepriseRepository,
                         DepartementRepository departementRepository) {
        this.employeRepository = employeRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.departementRepository = departementRepository;
    }

    // Récupérer tous les employés
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    // Récupérer un employé par matricule
    public Optional<Employe> getEmployeById(Integer matricule) {
        return employeRepository.findById(matricule);
    }

    // Créer un employé
    public Employe saveEmploye(Employe employe) {
        // Gérer l'entreprise
        if (employe.getEntreprise() != null && employe.getEntreprise().getId() != null) {
            Entreprise entreprise = entrepriseRepository.findById(employe.getEntreprise().getId())
                    .orElse(null);
            employe.setEntreprise(entreprise);
        }
        
        // Gérer les départements
        if (employe.getDepartements() != null && !employe.getDepartements().isEmpty()) {
            List<Departement> departements = new ArrayList<>();
            for (Departement dep : employe.getDepartements()) {
                if (dep.getId() != null) {
                    departementRepository.findById(dep.getId()).ifPresent(departements::add);
                }
            }
            employe.setDepartements(departements);
        }
        
        return employeRepository.save(employe);
    }

    // Mettre à jour un employé
    public Employe updateEmploye(Integer matricule, Employe employeDetails) {
        Employe employe = employeRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec matricule : " + matricule));
        
        employe.setNom(employeDetails.getNom());
        employe.setPrenom(employeDetails.getPrenom());
        employe.setFonction(employeDetails.getFonction());
        
        // Mettre à jour l'entreprise si fournie
        if (employeDetails.getEntreprise() != null && employeDetails.getEntreprise().getId() != null) {
            Entreprise entreprise = entrepriseRepository.findById(employeDetails.getEntreprise().getId())
                    .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));
            employe.setEntreprise(entreprise);
        }
        
        return employeRepository.save(employe);
    }

    // Supprimer un employé
    public void deleteEmploye(Integer matricule) {
        employeRepository.deleteById(matricule);
    }

    // Assigner un employé à une entreprise
    public Employe assignToEntreprise(Integer matricule, Integer entrepriseId) {
        Employe employe = employeRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        
        Entreprise entreprise = entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée"));
        
        employe.setEntreprise(entreprise);
        return employeRepository.save(employe);
    }

    // Assigner un employé à un département
    public Employe assignToDepartement(Integer matricule, Integer departementId) {
        Employe employe = employeRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new RuntimeException("Département non trouvé"));
        
        if (!employe.getDepartements().contains(departement)) {
            employe.getDepartements().add(departement);
            return employeRepository.save(employe);
        }
        
        return employe;
    }

    // Retirer un employé d'un département
    public Employe removeFromDepartement(Integer matricule, Integer departementId) {
        Employe employe = employeRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        
        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new RuntimeException("Département non trouvé"));
        
        employe.getDepartements().remove(departement);
        return employeRepository.save(employe);
    }
}
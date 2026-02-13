package edu.isetjb.l2dsi.envdev.springdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.isetjb.l2dsi.envdev.springdemo.model.Employe;
import edu.isetjb.l2dsi.envdev.springdemo.repository.EmployeRepository;

@Service
public class EmployeService {

    private final EmployeRepository employeRepository;

    public EmployeService(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    // Récupérer tous les employés
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    // Récupérer un employé par matricule
    public Optional<Employe> getEmployeById(Integer matricule) {
        return employeRepository.findById(matricule);
    }

    // Ajouter un employé
    public Employe saveEmploye(Employe employe) {
        return employeRepository.save(employe);
    }

    // Mettre à jour un employé
    public Employe updateEmploye(Integer matricule, Employe employeDetails) {
        Employe employe = employeRepository.findById(matricule)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé avec matricule : " + matricule));
        employe.setNom(employeDetails.getNom());
        return employeRepository.save(employe);
    }

    // Supprimer un employé
    public void deleteEmploye(Integer matricule) {
        employeRepository.deleteById(matricule);
    }
}

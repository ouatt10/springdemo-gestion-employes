package edu.isetjb.l2dsi.envdev.springdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.isetjb.l2dsi.envdev.springdemo.model.Departement;
import edu.isetjb.l2dsi.envdev.springdemo.repository.DepartementRepository;

@Service
public class DepartementService {

    private final DepartementRepository departementRepository;

    public DepartementService(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }

    // Récupérer tous les départements
    public List<Departement> getAllDepartements() {
        return departementRepository.findAll();
    }

    // Récupérer un département par ID
    public Optional<Departement> getDepartementById(Integer id) {
        return departementRepository.findById(id);
    }

    // Créer un département
    public Departement saveDepartement(Departement departement) {
        return departementRepository.save(departement);
    }

    // Mettre à jour un département
    public Departement updateDepartement(Integer id, Departement departementDetails) {
        Departement departement = departementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Département non trouvé avec id : " + id));
        
        departement.setNom(departementDetails.getNom());
        
        return departementRepository.save(departement);
    }

    // Supprimer un département
    public void deleteDepartement(Integer id) {
        departementRepository.deleteById(id);
    }
}
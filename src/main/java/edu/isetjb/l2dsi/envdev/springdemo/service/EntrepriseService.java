package edu.isetjb.l2dsi.envdev.springdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import edu.isetjb.l2dsi.envdev.springdemo.model.Entreprise;
import edu.isetjb.l2dsi.envdev.springdemo.repository.EntrepriseRepository;

@Service
public class EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    // Récupérer toutes les entreprises
    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    // Récupérer une entreprise par ID
    public Optional<Entreprise> getEntrepriseById(Integer id) {
        return entrepriseRepository.findById(id);
    }

    // Créer une entreprise
    public Entreprise saveEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    // Mettre à jour une entreprise
    public Entreprise updateEntreprise(Integer id, Entreprise entrepriseDetails) {
        Entreprise entreprise = entrepriseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée avec id : " + id));
        
        entreprise.setNom(entrepriseDetails.getNom());
        entreprise.setAdresse(entrepriseDetails.getAdresse());
        
        return entrepriseRepository.save(entreprise);
    }

    // Supprimer une entreprise
    public void deleteEntreprise(Integer id) {
        entrepriseRepository.deleteById(id);
    }
}
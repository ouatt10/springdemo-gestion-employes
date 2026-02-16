package edu.isetjb.l2dsi.envdev.springdemo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.isetjb.l2dsi.envdev.springdemo.model.Departement;
import edu.isetjb.l2dsi.envdev.springdemo.model.Entreprise;
import edu.isetjb.l2dsi.envdev.springdemo.repository.EntrepriseRepository;
import edu.isetjb.l2dsi.envdev.springdemo.service.DepartementService;

@RestController
@RequestMapping("/departements")
@CrossOrigin(origins = "*") 
public class DepartementController {

    private final DepartementService departementService;
    
    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
    public List<Departement> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepartement(@PathVariable Integer id) {
        return departementService.getDepartementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST CORRIGÉ (SANS printStackTrace)
    @PostMapping
    public ResponseEntity<?> createDepartement(@RequestBody Map<String, String> data) {
        try {
            List<Entreprise> entreprises = entrepriseRepository.findAll();
            if (entreprises.isEmpty()) {
                return ResponseEntity.badRequest()
                    .body(Map.of("error", "Créez d'abord une entreprise !"));
            }
            
            Departement dept = new Departement();
            dept.setNom(data.get("nom"));
            dept.setEntreprise(entreprises.get(0));  // ✅ FIX BUG 500
            
            Departement saved = departementService.saveDepartement(dept);
            return ResponseEntity.ok(saved);
            
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(Map.of("error", "Erreur création département"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartement(@PathVariable Integer id,
                                             @RequestBody Map<String, String> data) {
        try {
            List<Entreprise> entreprises = entrepriseRepository.findAll();
            if (entreprises.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }
            
            return departementService.getDepartementById(id)
                .map(dept -> {
                    dept.setNom(data.get("nom"));
                    dept.setEntreprise(entreprises.get(0));
                    return ResponseEntity.ok(departementService.saveDepartement(dept));
                })
                .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Integer id) {
        departementService.deleteDepartement(id);
        return ResponseEntity.noContent().build();
    }
}

package edu.isetjb.l2dsi.envdev.springdemo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.isetjb.l2dsi.envdev.springdemo.model.Departement;
import edu.isetjb.l2dsi.envdev.springdemo.service.DepartementService;

@RestController
@RequestMapping("/departements")
public class DepartementController {

    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    // GET /departements
    @GetMapping
    public List<Departement> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    // GET /departements/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Departement> getDepartement(@PathVariable Integer id) {
        return departementService.getDepartementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /departements
    @PostMapping
    public Departement createDepartement(@RequestBody Departement departement) {
        return departementService.saveDepartement(departement);
    }

    // PUT /departements/{id}
    @PutMapping("/{id}")
    public Departement updateDepartement(@PathVariable Integer id,
                                        @RequestBody Departement departement) {
        return departementService.updateDepartement(id, departement);
    }

    // DELETE /departements/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable Integer id) {
        departementService.deleteDepartement(id);
        return ResponseEntity.noContent().build();
    }
}
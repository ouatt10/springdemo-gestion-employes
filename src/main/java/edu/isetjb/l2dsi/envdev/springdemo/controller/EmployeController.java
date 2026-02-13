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

import edu.isetjb.l2dsi.envdev.springdemo.model.Employe;
import edu.isetjb.l2dsi.envdev.springdemo.service.EmployeService;

@RestController
@RequestMapping("/employes")
public class EmployeController {

    private final EmployeService employeService;

    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    // GET /employes
    @GetMapping
    public List<Employe> getAllEmployes() {
        return employeService.getAllEmployes();
    }

    // GET /employes/{matricule}
    @GetMapping("/{matricule}")
    public ResponseEntity<Employe> getEmploye(@PathVariable Integer matricule) {
        return employeService.getEmployeById(matricule)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /employes
    @PostMapping
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeService.saveEmploye(employe);
    }

    // PUT /employes/{matricule}
    @PutMapping("/{matricule}")
    public Employe updateEmploye(@PathVariable Integer matricule,
                                 @RequestBody Employe employe) {
        return employeService.updateEmploye(matricule, employe);
    }

    // DELETE /employes/{matricule}
    @DeleteMapping("/{matricule}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Integer matricule) {
        employeService.deleteEmploye(matricule);
        return ResponseEntity.noContent().build();
    }
}
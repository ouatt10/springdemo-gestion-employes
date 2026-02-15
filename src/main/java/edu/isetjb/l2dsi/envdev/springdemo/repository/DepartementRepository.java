package edu.isetjb.l2dsi.envdev.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.isetjb.l2dsi.envdev.springdemo.model.Departement;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
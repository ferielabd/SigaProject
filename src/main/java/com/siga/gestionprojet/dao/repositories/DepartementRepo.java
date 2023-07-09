package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepo extends JpaRepository<Departement,Integer> {
}

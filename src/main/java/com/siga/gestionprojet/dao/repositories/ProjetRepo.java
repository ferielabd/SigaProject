package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetRepo extends JpaRepository<Projet,String> {
}

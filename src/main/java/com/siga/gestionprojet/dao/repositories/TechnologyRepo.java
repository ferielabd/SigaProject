package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologyRepo extends JpaRepository<Technology,Integer> {
}

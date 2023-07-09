package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialiteRepo extends JpaRepository<Specialite,Integer> {
}

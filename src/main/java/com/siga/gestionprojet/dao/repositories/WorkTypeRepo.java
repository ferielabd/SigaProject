package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.WorkType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkTypeRepo extends JpaRepository<WorkType, Integer> {
}
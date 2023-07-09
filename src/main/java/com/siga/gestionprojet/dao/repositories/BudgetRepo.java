package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepo extends JpaRepository<Budget,Integer> {
}

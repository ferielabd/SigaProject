package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Integer> {
}

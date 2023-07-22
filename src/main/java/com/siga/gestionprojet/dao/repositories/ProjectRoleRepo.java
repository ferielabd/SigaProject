package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.ProjectRole;
import com.siga.gestionprojet.dao.entities.ProjectRoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProjectRoleRepo extends JpaRepository<ProjectRole, Integer> {
    Optional<ProjectRole> findByName(ProjectRoleName projectRoleName);
    List<ProjectRole> findByNameIn(List<ProjectRoleName> projectRoleNames);
}
package com.siga.gestionprojet.Services.interfaces;

import com.siga.gestionprojet.dao.entities.ProjectRole;
import com.siga.gestionprojet.dto.ProjectRoleName;
import com.siga.gestionprojet.dao.entities.Projet;
import com.siga.gestionprojet.dao.entities.WorkType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IProject {


    Projet update(Integer projectId, String name, String description, LocalDate start, LocalDate end, List<WorkType> workTypes);

    Projet create(String name, String description, LocalDate start, LocalDate end, List<WorkType> workTypes);

    List<Projet> findAll();
    Optional<Projet> findById(Integer id);
    List<Projet> findAllCurrentlyAssignedProjectsByUserId(Integer userId);
    void deleteById(Integer id);
    Optional<ProjectRole> findProjectRoleByName(ProjectRoleName projectRoleName);
    List<ProjectRole> findAllProjectRoles();
    boolean isUserAssignedToProject(Integer userId, Integer projectId);

    boolean isUserAssignedToProjectAndHasAnyRole(Integer userId, Integer projectId, String... roles);
}

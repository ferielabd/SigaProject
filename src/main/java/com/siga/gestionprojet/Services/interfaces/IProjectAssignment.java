package com.siga.gestionprojet.Services.interfaces;

import com.siga.gestionprojet.dao.entities.ProjectAssignment;
import com.siga.gestionprojet.dto.ProjectRoleName;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IProjectAssignment {
    List<ProjectAssignment> findByProjectId(Integer projectId);
    Optional<ProjectAssignment> findById(Integer id);
    ProjectAssignment create(Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo);
    ProjectAssignment create(Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo, List<ProjectRoleName> projectRoleNames);
    ProjectAssignment update(Integer projectAssignmentId, Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo, List<ProjectRoleName> projectRoleNames);
    void deleteProjectAssignmentById(Integer id);
}

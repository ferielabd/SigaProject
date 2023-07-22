package com.siga.gestionprojet.Services.Classes;

import com.siga.gestionprojet.Services.interfaces.IProject;
import com.siga.gestionprojet.dao.entities.ProjectRole;
import com.siga.gestionprojet.dto.ProjectRoleName;
import com.siga.gestionprojet.dao.entities.Projet;
import com.siga.gestionprojet.dao.entities.WorkType;
import com.siga.gestionprojet.dto.*;
import com.siga.gestionprojet.exception.ProjectNotFoundException;
import com.siga.gestionprojet.mapper.ProjectModelMapper;
import lombok.var;
import org.modelmapper.internal.util.Assert;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService implements IProject {


    private DataAccessApi dataAccessApi;


    private ProjectModelMapper projectModelMapper;

    @Override
    public Projet create(String name, String description, LocalDate start, LocalDate end, List<WorkType> workTypes) {
        Assert.isTrue(!name.isEmpty(), "name of a project cannot be blank");
        Assert.notNull(start, "start of a project cannot be null");
        Projet result = createOrUpdateProject(null, name, description, start, end, workTypes);
        return result;
    }

    @Override
    public Projet update(Integer projectId, String name, String description, LocalDate start, LocalDate end, List<WorkType> workTypes) {
        Assert.isTrue(!name.isEmpty(), "name of a project cannot be blank");
        Assert.notNull(start, "start of a project cannot be null");
        Assert.notNull(projectId, "project id cannot be null");
        dataAccessApi.findProjectById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        Projet result = createOrUpdateProject(projectId, name, description, start, end, workTypes);
        return result;
    }

    private Projet createOrUpdateProject(Integer projectId, String name, String description, LocalDate start, LocalDate end, List<WorkType> workTypes) {
        ProjectDTO projectDTO = new ProjectDTO();
        if (projectId != null) {
            projectDTO.setId(projectId);
        }
        projectDTO.setName(name);
        projectDTO.setDescription(description);
        projectDTO.setStart(start);
        projectDTO.setEnd(end);
        projectDTO.setWorkTypes(map(workTypes));
        projectDTO = dataAccessApi.createOrUpdateProject(projectDTO);
        return map(projectDTO);
    }

    @Override
    public List<Projet> findAll() {
        List<ProjectDTO> projectDTOs = dataAccessApi.findAllProjects();
        List<Projet> projects = projectDTOs.stream().map(this::map).collect(Collectors.toList());
        return projects;
    }

    @Override
    public Optional<Projet> findById(Integer id) {
        Optional<ProjectDTO> optionalProjectDTO = dataAccessApi.findProjectById(id);
        Optional<Projet> result = optionalProjectDTO.map(this::map);
        return result;
    }

    @Override
    public List<Projet> findAllCurrentlyAssignedProjectsByUserId(Integer userId) {
        Assert.notNull(userId, "user id cannot be null");
        List<ProjectDTO> projectDTOs = dataAccessApi.findAllAssignedProjectsWhereValidTimeOverlapsByUserId(LocalDate.now(), userId);
        List<Projet> projects = projectDTOs.stream().map(this::map).collect(Collectors.toList());
        return projects;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<ProjectDTO> optionalProjectDTO = dataAccessApi.findProjectById(id);
        optionalProjectDTO.ifPresent(p -> dataAccessApi.deleteProjectById(id)
        );
    }

    @Override
    public Optional<ProjectRole> findProjectRoleByName(ProjectRoleName projectRoleName) {
        Optional<ProjectRoleDTO> projectRoleDTO = dataAccessApi.findProjectRoleByName(map(projectRoleName));
        return projectRoleDTO.map(this::map);
    }

    @Override
    public List<ProjectRole> findAllProjectRoles() {
        List<ProjectRoleDTO> projectRoleDTOs = dataAccessApi.findAllProjectRoles();
        List<ProjectRole> projectRoles = projectRoleDTOs.stream().map(this::map).collect(Collectors.toList());
        return projectRoles;
    }

    @Override
    public boolean isUserAssignedToProject(Integer userId, Integer projectId) {
        List<ProjectAssignmentDTO> projectAssignments = dataAccessApi.findProjectAssignmentsByProjectIdAndUserId(projectId, userId);
        var isAssigned = projectAssignments.stream().anyMatch(this::isAssignmentValid);
        return isAssigned;
    }

    @Override
    public boolean isUserAssignedToProjectAndHasAnyRole(Integer userId, Integer projectId, String... roles) {
        List<ProjectAssignmentDTO> projectAssignments = dataAccessApi.findProjectAssignmentsByProjectIdAndUserId(projectId, userId);
        var isAssigned = projectAssignments.stream().anyMatch(projectAssignmentDTO ->
                isAssignmentValid(projectAssignmentDTO)
                        && projectAssignmentDTO.getProjectRoles().stream().anyMatch(roleDTO -> Arrays.asList(roles).contains(roleDTO.getName().toString())));
        return isAssigned;
    }

    private boolean isAssignmentValid(ProjectAssignmentDTO projectAssignmentDTO) {
        var now = LocalDate.now();
        return !now.isBefore(projectAssignmentDTO.getValidFrom()) && (projectAssignmentDTO.getValidTo() == null || !now.isAfter(projectAssignmentDTO.getValidTo()));
    }

    private List<WorkTypeDTO> map(List<WorkType> workTypes) {
        return workTypes.stream().map(w -> projectModelMapper.map(w, WorkTypeDTO.class)).collect(Collectors.toList());
    }

    private ProjectRoleName map(ProjectRoleName projectRoleName) {
        return projectModelMapper.map(projectRoleName, ProjectRoleName.class);
    }

    private ProjectRole map(ProjectRoleDTO projectRoleDTO) {
        ProjectRole projectRole = projectModelMapper.map(projectRoleDTO, ProjectRole.class);
        return projectRole;
    }

    private Projet map(ProjectDTO projectDTO) {
        Projet project = projectModelMapper.map(projectDTO, Projet.class);
        return project;
    }
}
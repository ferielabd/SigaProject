package com.siga.gestionprojet.Services.Classes;

import com.siga.gestionprojet.Services.interfaces.IProjectAssignment;
import com.siga.gestionprojet.dao.entities.ProjectAssignment;
import com.siga.gestionprojet.dto.DataAccessApi;
import com.siga.gestionprojet.dto.ProjectAssignmentDTO;
import com.siga.gestionprojet.dto.ProjectRoleName;
import com.siga.gestionprojet.dto.*;
import com.siga.gestionprojet.exception.ProjectAssignmentException;
import com.siga.gestionprojet.exception.ProjectAssignmentNotFoundException;
import com.siga.gestionprojet.mapper.ProjectModelMapper;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectAssignmentService implements IProjectAssignment {

    private static final Logger LOGGER = LogManager.getLogger();

    private DataAccessApi dataAccessApi;


    private ProjectModelMapper projectModelMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<ProjectAssignment> findByProjectId(Integer projectId) {
        Assert.notNull(projectId, "project id cannot be null");
        List<ProjectAssignmentDTO> projectAssignmentDTOs = dataAccessApi.findProjectAssignmentsByProjectId(projectId);
        List<ProjectAssignment> projectAssignments = projectAssignmentDTOs.stream().map(this::map).collect(Collectors.toList());
        return projectAssignments;
    }

    @Override
    public Optional<ProjectAssignment> findById(Integer id) {
        Assert.notNull(id, "project assignment id cannot be null");
        Optional<ProjectAssignmentDTO> projectAssignmentDTO = dataAccessApi.findProjectAssignmentById(id);
        return projectAssignmentDTO.map(this::map);
    }

    @Override
    public ProjectAssignment create(Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo) {
        return create(projectId, userId, validFrom, validTo, Arrays.asList(ProjectRoleName.MEMBER));
    }

    @Override
    public ProjectAssignment create(Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo, List<ProjectRoleName> projectRoleNames) {
        if (!memberRoleExists(projectRoleNames)) {
            LOGGER.info("Project assignment creation - missing MEMBER project role... MEMBER project role added as default");
            projectRoleNames.add(ProjectRoleName.MEMBER);
        }
        return createOrUpdate(null, projectId, userId, validFrom, validTo, projectRoleNames);
    }

    @Override
    public ProjectAssignment update(Integer projectAssignmentId, Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo, List<ProjectRoleName> projectRoleNames) {
        findById(projectAssignmentId).orElseThrow(() -> new ProjectAssignmentNotFoundException(projectAssignmentId));
        return createOrUpdate(projectAssignmentId, projectId, userId, validFrom, validTo, projectRoleNames);
    }

    @Override
    public void deleteProjectAssignmentById(Integer id) {
        Assert.notNull(id, "id cannot be null");
        Optional<ProjectAssignmentDTO> projectAssignmentDTO = dataAccessApi.findProjectAssignmentById(id);
        projectAssignmentDTO.ifPresent(p -> dataAccessApi.deleteProjectAssignmentById(id));
    }

    private ProjectAssignment createOrUpdate(Integer projectAssignmentId, Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo, List<ProjectRoleName> projectRoleNames) {
        checkConstraintsForCreateOrUpdate(projectId, userId, validFrom, validTo, projectRoleNames, projectAssignmentId);
        List<ProjectRoleDTO> projectRoleDTOs = dataAccessApi.findAllProjectRolesIn(projectRoleNames.stream().map(this::map).collect(Collectors.toList()));
        Assert.isTrue(projectRoleDTOs.size() == projectRoleNames.size(), "All project roles must be persisted in DB");
        ProjectAssignmentDTOLight projectAssignmentDTO = new ProjectAssignmentDTOLight();
        if (projectAssignmentId != null) {
            projectAssignmentDTO.setId(projectAssignmentId);
        }
        projectAssignmentDTO.setProjectId(projectId);
        projectAssignmentDTO.setUserId(userId);
        projectAssignmentDTO.setValidFrom(validFrom);
        projectAssignmentDTO.setValidTo(validTo);
        projectAssignmentDTO.setProjectRoles(new HashSet<>(projectRoleDTOs));
        ProjectAssignmentDTOLight updatedProjectAssignmentDTO = dataAccessApi.createOrUpdateProjectAssignment(projectAssignmentDTO);
        return findById(updatedProjectAssignmentDTO.getId()).get();
    }

    private void checkConstraintsForCreateOrUpdate(Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo, List<ProjectRoleName> projectRoleNames, Integer projectAssignmentId) {
        Assert.notNull(projectId, "project id cannot be null");
        Assert.notNull(userId, "user id cannot be null");
        Assert.notNull(validFrom, "valid from cannot be null");
        if (validTo != null) {
            Assert.isTrue(validTo.isAfter(validFrom), "validTo must be after validFrom");
        }

        if (!memberRoleExists(projectRoleNames)) {
            throw new ProjectAssignmentException("MEMBER project role in project assignment must exist and cannot be removed");
        }

        userService.SelectById(userId);
        if (assignmentWithProjectAndUserAtGivenDateAlreadyExists(projectId, userId, validFrom, validTo, projectAssignmentId)) {
            throw new ProjectAssignmentException("Project assignment with user = " + userId + " and project = " + projectId + " already exists in given time from = " + validFrom.toString() + " and to = " + (validTo == null ? "[null]" : validTo.toString()));
        }
    }

    private boolean memberRoleExists(List<ProjectRoleName> projectRoleNames) {
        return projectRoleNames.stream().anyMatch(name -> name.equals(ProjectRoleName.MEMBER));
    }

    private boolean assignmentWithProjectAndUserAtGivenDateAlreadyExists(Integer projectId, Integer userId, LocalDate validFrom, LocalDate validTo, Integer projectAssignmentId) {
        List<ProjectAssignmentDTO> projectAssignments = dataAccessApi.findProjectAssignmentsByProjectIdAndUserId(projectId, userId);
        for (ProjectAssignmentDTO projectAssignment : projectAssignments) {
            if (projectAssignment.getId().equals(projectAssignmentId)) {
                continue;
            }

            if (projectAssignment.getValidTo() == null) {
                if (validTo == null || !validTo.isBefore(projectAssignment.getValidFrom())) {
                    return true;
                }
            } else if (!(validFrom.isAfter(projectAssignment.getValidTo()) || (validTo != null && validTo.isBefore(projectAssignment.getValidFrom())))) {
                return true;
            }
        }
        return false;
    }

    private ProjectAssignment map(ProjectAssignmentDTO projectAssignmentDTO) {
        ProjectAssignment projectAssignment = projectModelMapper.map(projectAssignmentDTO, ProjectAssignment.class);
        return projectAssignment;
    }

    private ProjectRoleName map(ProjectRoleName projectRoleName) {
        return projectModelMapper.map(projectRoleName,ProjectRoleName.class);
    }

}

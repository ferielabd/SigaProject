package com.siga.gestionprojet.Restcontrollers;

import com.siga.gestionprojet.Services.interfaces.IProject;
import com.siga.gestionprojet.Services.interfaces.IProjectAssignment;
import com.siga.gestionprojet.dao.entities.ProjectAssignment;
import com.siga.gestionprojet.dao.entities.ProjectRole;
import com.siga.gestionprojet.dao.entities.Projet;
import com.siga.gestionprojet.dao.entities.WorkType;
import com.siga.gestionprojet.dto.ProjectAssignmentDTO;
import com.siga.gestionprojet.dto.ProjectDTO;
import com.siga.gestionprojet.dto.ProjectRoleDTO;
import com.siga.gestionprojet.dto.WorkTypeDTO;
import com.siga.gestionprojet.dto.request.CreateOrUpdateProjectRequest;
import com.siga.gestionprojet.dto.response.ProjectAssignmentsResponse;
import com.siga.gestionprojet.dto.response.ProjectRolesResponse;
import com.siga.gestionprojet.dto.response.ProjectsResponse;
import com.siga.gestionprojet.mapper.RestModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private IProject projectService;

    @Autowired
    private IProjectAssignment projectAssignmentService;


    private RestModelMapper restModelMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectsResponse> getAll() {
        List<Projet> projects = projectService.findAll();
        List<ProjectDTO> projectDTOs = projects.stream().map(p -> restModelMapper.map(p, ProjectDTO.class)).collect(Collectors.toList());
        ProjectsResponse projectsResponse = new ProjectsResponse();
        projectsResponse.setProjects(projectDTOs);
        return ResponseEntity.ok(projectsResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or @securityAccessServiceImpl.hasProjectRole(#id, 'PROJECT_MANAGER')")
    public ResponseEntity<ProjectDTO> getById(@PathVariable("id") Integer id) {
        Optional<Projet> projectOptional = projectService.findById(id);
        ResponseEntity<ProjectDTO> response = projectOptional.map(u -> ResponseEntity.ok(restModelMapper.map(u, ProjectDTO.class))).orElseGet(() -> ResponseEntity.notFound().build());
        return response;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectDTO> create(@Valid @RequestBody CreateOrUpdateProjectRequest request) {
        Projet project = projectService.create(request.getName(), request.getDescription(), request.getStart(), request.getEnd(), map(request.getWorkTypes()));
        ProjectDTO result = restModelMapper.map(project, ProjectDTO.class);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CreateOrUpdateProjectRequest request) {
        Projet updatedProject = projectService.update(id, request.getName(), request.getDescription(), request.getStart(), request.getEnd(), map(request.getWorkTypes()));
        ProjectDTO result = restModelMapper.map(updatedProject, ProjectDTO.class);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        projectService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/project-assignments")
    @PreAuthorize("hasAuthority('ADMIN') or @securityAccessServiceImpl.hasProjectRole(#projectId, 'PROJECT_MANAGER')")
    public ResponseEntity<ProjectAssignmentsResponse> getProjectAssignments(@PathVariable("id") Integer projectId) {
        List<ProjectAssignment> projectAssignments = projectAssignmentService.findByProjectId(projectId);
        ProjectAssignmentsResponse projectAssignmentsResponse = new ProjectAssignmentsResponse();
        List<ProjectAssignmentDTO> projectAssignmentDTOs = projectAssignments.stream().map(a -> restModelMapper.map(a, ProjectAssignmentDTO.class)).collect(Collectors.toList());
        projectAssignmentsResponse.setProjectAssignments(projectAssignmentDTOs);
        return ResponseEntity.ok(projectAssignmentsResponse);
    }

    @GetMapping("/roles")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectRolesResponse> getProjectRoles() {
        List<ProjectRole> projectRoles = projectService.findAllProjectRoles();
        ProjectRolesResponse projectRolesResponse = new ProjectRolesResponse();
        projectRolesResponse.setProjectRoles(projectRoles.stream().map(role -> restModelMapper.map(role, ProjectRoleDTO.class)).collect(Collectors.toList()));
        return ResponseEntity.ok(projectRolesResponse);
    }

    private List<WorkType> map(List<WorkTypeDTO> workTypes) {
        return workTypes.stream().map(w -> restModelMapper.map(w, WorkType.class)).collect(Collectors.toList());
    }
}

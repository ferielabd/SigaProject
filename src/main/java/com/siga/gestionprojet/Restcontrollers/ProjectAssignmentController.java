package com.siga.gestionprojet.Restcontrollers;

import com.siga.gestionprojet.Services.interfaces.IProjectAssignment;
import com.siga.gestionprojet.dao.entities.ProjectAssignment;
import com.siga.gestionprojet.dto.ProjectAssignmentDTO;
import com.siga.gestionprojet.dto.request.CreateOrUpdateProjectAssignmentRequest;
import com.siga.gestionprojet.exception.ProjectAssignmentNotFoundException;
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
import java.util.Optional;

@RestController
@RequestMapping("/project-assignments")
public class ProjectAssignmentController {

    @Autowired
    private IProjectAssignment projectAssignmentService;


    private RestModelMapper restModelMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectAssignmentDTO> getById(@PathVariable("id") Integer id) {
        Optional<ProjectAssignment> projectAssignmentOptional = projectAssignmentService.findById(id);
        ProjectAssignmentDTO projectAssignmentDTO = projectAssignmentOptional.map(p -> restModelMapper.map(p, ProjectAssignmentDTO.class)).orElseThrow(() -> new ProjectAssignmentNotFoundException(id));
        return ResponseEntity.ok(projectAssignmentDTO);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectAssignmentDTO> create(@Valid @RequestBody CreateOrUpdateProjectAssignmentRequest request) {
        ProjectAssignment projectAssignment = projectAssignmentService.create(request.getProjectId(), request.getUserId(), request.getValidFrom(), request.getValidTo(), request.getProjectRoleNames());
        ProjectAssignmentDTO result = restModelMapper.map(projectAssignment, ProjectAssignmentDTO.class);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProjectAssignmentDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody CreateOrUpdateProjectAssignmentRequest request) {
        ProjectAssignment projectAssignment = projectAssignmentService.update(id, request.getProjectId(), request.getUserId(), request.getValidFrom(), request.getValidTo(), request.getProjectRoleNames());
        ProjectAssignmentDTO result = restModelMapper.map(projectAssignment, ProjectAssignmentDTO.class);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        projectAssignmentService.deleteProjectAssignmentById(id);
        return ResponseEntity.ok().build();
    }

}

package com.siga.gestionprojet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siga.gestionprojet.dto.ProjectAssignmentDTO;


import java.util.ArrayList;
import java.util.List;

public class ProjectAssignmentsResponse {

    @JsonProperty("projectAssignments")
    List<ProjectAssignmentDTO> projectAssignments = new ArrayList<>();

    public List<ProjectAssignmentDTO> getProjectAssignments() {
        return projectAssignments;
    }

    public void setProjectAssignments(List<ProjectAssignmentDTO> projectAssignments) {
        this.projectAssignments = projectAssignments;
    }
}

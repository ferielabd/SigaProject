package com.siga.gestionprojet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siga.gestionprojet.dto.ProjectDTO;


import java.util.ArrayList;
import java.util.List;

public class ProjectsResponse {

    @JsonProperty("projects")
    private List<ProjectDTO> projects = new ArrayList<>();

    public List<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectDTO> projects) {
        this.projects = projects;
    }
}

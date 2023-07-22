package com.siga.gestionprojet.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siga.gestionprojet.dto.ProjectRoleDTO;


import java.util.ArrayList;
import java.util.List;

public class ProjectRolesResponse {

    @JsonProperty("projectRoles")
    private List<ProjectRoleDTO> projectRoles = new ArrayList<>();

    public List<ProjectRoleDTO> getProjectRoles() {
        return projectRoles;
    }

    public void setProjectRoles(List<ProjectRoleDTO> projectRoles) {
        this.projectRoles = projectRoles;
    }
}

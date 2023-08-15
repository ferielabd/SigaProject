package com.siga.gestionprojet.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectAssignmentDTOLight {

    private Integer id;
    private Integer projectId;
    private LocalDate validFrom;
    private LocalDate validTo;
    private Integer userId;
    private Set<ProjectRoleDTO> projectRoles = new HashSet<>();


}

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
public class ProjectAssignmentDTO {

    private Integer id;
    private ProjectDTOLight project;
    private LocalDate validFrom;
    private LocalDate validTo;
    private UserDTOLight user;
    private Set<ProjectRoleDTO> projectRoles = new HashSet<>();


}

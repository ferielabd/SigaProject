package com.siga.gestionprojet.dto;
import lombok.*;

import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectRoleDTO {

    private Integer id;
    private ProjectRoleName name;
    private String description;





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectRoleDTO that = (ProjectRoleDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

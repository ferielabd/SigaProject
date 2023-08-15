package com.siga.gestionprojet.dto;
import lombok.*;

import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleDTO {

    private Integer id;
    private UserRoleName name;
    private String description;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleDTO that = (UserRoleDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

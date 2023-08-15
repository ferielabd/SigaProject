package com.siga.gestionprojet.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String pictureUrl;
    private AuthProvider authProvider;
    private List<UserRoleDTO> userRoles = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

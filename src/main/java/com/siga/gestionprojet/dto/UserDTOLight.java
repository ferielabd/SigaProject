package com.siga.gestionprojet.dto;

import java.util.Objects;

public class UserDTOLight {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String pictureUrl;
    private AuthProvider authProvider;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTOLight that = (UserDTOLight) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

package com.siga.gestionprojet.dto;
import lombok.*;

import java.time.LocalDate;

import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTOLight {

    private Integer id;
    private String name;
    private String description;
    private LocalDate start;
    private LocalDate end;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDTOLight that = (ProjectDTOLight) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

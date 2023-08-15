package com.siga.gestionprojet.dto;

import lombok.*;

import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkTypeDTO {

    private Integer id;
    private String name;
    private String description;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkTypeDTO that = (WorkTypeDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

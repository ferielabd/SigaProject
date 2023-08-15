package com.siga.gestionprojet.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkRecordDTOLight {

    private Integer id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private Integer userId;
    private Integer projectId;
    private Integer workTypeId;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkRecordDTOLight that = (WorkRecordDTOLight) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

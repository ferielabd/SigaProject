package com.siga.gestionprojet.dto;
import java.time.LocalDateTime;
import java.util.Objects;

public class WorkRecordDTO {

    private Integer id;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private ProjectDTOLight project;
    private WorkTypeDTO workType;
    private UserDTOLight user;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkRecordDTO that = (WorkRecordDTO) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

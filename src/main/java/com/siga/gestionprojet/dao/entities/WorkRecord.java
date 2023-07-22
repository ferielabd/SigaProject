package com.siga.gestionprojet.dao.entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class WorkRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     Integer id;

    @NotNull
    @Column(name = "date_from")
     LocalDateTime dateFrom;

    @NotNull
    @Column(name = "date_to")
     LocalDateTime dateTo;

    @NotEmpty
    @Column(name = "description")
     String description;

    @NotNull
    @Column(name = "date_created")
     LocalDateTime dateCreated;

    @Column(name = "date_updated")
     LocalDateTime dateUpdated;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
     User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_project")
     Projet project;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_work_type")
     WorkType workType;
}
package com.siga.gestionprojet.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Projet implements Serializable {
    @Id
    String matriculeP;
    String libelleP;
    LocalDate startDate;
    LocalDate endDate;
    String typeProj;
    String description;
    @OneToMany(mappedBy = "projet")
    List<Budget> budgets;

    @ManyToMany(mappedBy = "projetListTec")
    List<Technology> technologies;

    @OneToMany(mappedBy = "projetCP")
    List<ContratProjet> contratProjetsP;
}

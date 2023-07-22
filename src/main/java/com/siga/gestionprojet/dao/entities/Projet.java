package com.siga.gestionprojet.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedEntityGraphs(value = {
        @NamedEntityGraph(
                name = "Project.projectAssignmentsWithProjectRoles",
                attributeNodes = @NamedAttributeNode(value = "projectAssignments", subgraph = "ProjectAssignment.projectRoles"),
                subgraphs = @NamedSubgraph(name = "ProjectAssignment.projectRoles", attributeNodes = @NamedAttributeNode(value = "projectRoles"))
        ),
        @NamedEntityGraph(
                name = "Project.workTypes",
                attributeNodes = @NamedAttributeNode(value = "workTypes")
        )
})
@Entity
public class Projet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @NotEmpty
    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @NotNull
    @Column(name = "start")
    LocalDate start;

    @Column(name = "\"end\"")
    LocalDate end;
    @Column(name = "TypeProj")
    String typeProj;

    @OneToMany(mappedBy = "projet")
    List<Budget> budgets;
     @ManyToOne
      Client client;

    @OneToMany(mappedBy = "project")
    private Set<ProjectAssignment> projectAssignments = new HashSet<>();

    @OneToMany(mappedBy = "project")
    private Set<WorkRecord> workRecords = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "project_work_type",
            joinColumns = @JoinColumn(name = "id_project"), inverseJoinColumns = @JoinColumn(name = "id_work_type"))
    private Set<WorkType> workTypes = new HashSet<>();
}
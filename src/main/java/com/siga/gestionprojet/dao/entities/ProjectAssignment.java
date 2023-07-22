package com.siga.gestionprojet.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NamedEntityGraph(
        name = "ProjectAssignment.projectRoles",
        attributeNodes = @NamedAttributeNode(value = "projectRoles")
)
@Entity
public class ProjectAssignment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     Integer id;

    @NotNull
    @Column(name = "valid_from")
     Date validFrom;

    @Column(name = "valid_to")
     Date validTo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_user")
     User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id_project")
     Projet project;

    @ManyToMany
    @JoinTable(name = "project_roles_assignment",
            joinColumns = @JoinColumn(name = "id_project_assignment"), inverseJoinColumns = @JoinColumn(name = "id_project_role"))
    Set<ProjectRole> projectRoles = new HashSet<>();
}

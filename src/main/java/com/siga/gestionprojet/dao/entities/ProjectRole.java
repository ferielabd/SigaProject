package com.siga.gestionprojet.dao.entities;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ProjectRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     Integer id;

    @NotNull
    @Column(name = "name", unique = true)
    @Enumerated(EnumType.STRING)
     ProjectRoleName name;

    @Column(name = "description")
     String description;

    @ManyToMany(mappedBy = "projectRoles")
     Set<ProjectAssignment> projectAssignments = new HashSet<>();
}

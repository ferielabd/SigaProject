package com.siga.gestionprojet.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
     Integer id;

    @NotEmpty
    @Column(name = "name", unique = true)
     String name;

    @Column(name = "description")
     String description;

    @ManyToMany(mappedBy = "workTypes")
     Set<Projet> projects = new HashSet<>();
}

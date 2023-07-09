package com.siga.gestionprojet.dao.entities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Technology implements Serializable {
    @Id
    int idT;
    String LibelleTec;
    float experience;
    @ManyToMany
    List<Projet> projetListTec;
}

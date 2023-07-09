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
public class User implements Serializable {
    @Id
    int cin;
    String login;
    String password;
    String address;
    String diploma;
    double salary;
    LocalDate dateBirth;

    @OneToMany(mappedBy = "userS")
    List<Specialite> specialiteUList;

    @OneToMany(mappedBy = "userR")
    List<Role> roleUList;
    @OneToMany(mappedBy = "userDep")
    List<Departement> departementUList;

    @OneToMany(mappedBy = "user")
    List<Participation_Proj> participation_projs_user;

    @OneToMany(mappedBy = "projet")
    List<Participation_Proj> participation_projs_p;

}

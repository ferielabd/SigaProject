package com.siga.gestionprojet.dao.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idUser;
    @Column(unique = true)
    long cin;

    String username;
    @Column(unique = true)
    String email;
    String password;
    String address;
    String diploma;
    double salary;
    Date dateNaissance=new Date();
    String verifPassword;

    long phone;

    boolean active;


    @OneToMany(mappedBy = "userS")
    List<Specialite> specialiteUList;

    @ManyToOne
    Role roles;
    @OneToMany(mappedBy = "userDep")
    List<Departement> departementUList;

    @OneToMany(mappedBy = "user")
    List<Participation_Proj> participation_projs_user;

    @OneToMany(mappedBy = "projet")
    List<Participation_Proj> participation_projs_p;

    @OneToOne
    @JsonIgnore
    ImageModel imageModel;

}

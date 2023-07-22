package com.siga.gestionprojet.dao.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @NotEmpty
    @Column(name = "username")
    String username;
    @NotEmpty
    @Email
    @Column(name = "email", unique = true)
    private String email;
    String password;
    String verifPassword;
    String address;
    String diploma;
    double salary;
    Date dateNaissance=new Date();


    long phone;

    boolean active;


    @OneToMany(mappedBy = "userS")
    List<Specialite> specialiteUList;


    @OneToMany(mappedBy = "userDep")
    List<Departement> departementUList;


    @OneToMany(mappedBy = "user")
    private Set<ProjectAssignment> projectAssignments = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<WorkRecord> workRecords = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_roles_assignment",
            joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_user_role"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    @JsonIgnore
    ImageModel imageModel;


}

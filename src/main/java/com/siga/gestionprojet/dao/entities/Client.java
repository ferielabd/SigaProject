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

public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idClient;
    String taxRegistration;
    String name;
    String mail;
    long phone;
    String address;

  @OneToMany(mappedBy = "client")
    List<Projet> projets;


}

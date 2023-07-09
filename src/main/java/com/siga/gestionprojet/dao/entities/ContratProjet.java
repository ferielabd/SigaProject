package com.siga.gestionprojet.dao.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContratProjet implements Serializable {

    @EmbeddedId
    ContratProjetKey id;

    @ManyToOne
    @MapsId("clientId")
    @JoinColumn(name = "client_id")
    Client client;

    @ManyToOne
    @MapsId("projetId")
    @JoinColumn(name = "projet_id")
    Projet projetCP;
    @Enumerated(EnumType.STRING)
    TypeContrat typeContrat;
    LocalDate signatureDate;

}

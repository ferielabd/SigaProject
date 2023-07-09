package com.siga.gestionprojet.dao.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class ContratProjetKey implements Serializable {
    @Column(name = "client_id")
    Long clientId;

    @Column(name = "projet_id")
    Long projetId;
}

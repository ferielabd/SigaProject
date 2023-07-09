package com.siga.gestionprojet.dao.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
public class UserProjetKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "projet_id")
    Long projetId;
}

package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,String> {
}

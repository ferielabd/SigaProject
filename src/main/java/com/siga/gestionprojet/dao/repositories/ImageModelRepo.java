package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageModelRepo extends JpaRepository<ImageModel,Integer> {
}
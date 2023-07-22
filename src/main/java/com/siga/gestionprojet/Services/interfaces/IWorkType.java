package com.siga.gestionprojet.Services.interfaces;
import com.siga.gestionprojet.dao.entities.WorkType;

import java.util.List;
import java.util.Optional;
public interface IWorkType {

    WorkType create(String name, String description);
    List<WorkType> findAll();
    Optional<WorkType> findById(Integer workTypeId);
    WorkType update(Integer workTypeId, String name, String description);
    void deleteById(Integer workTypeId);
}

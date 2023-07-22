package com.siga.gestionprojet.Services.Classes;
import com.siga.gestionprojet.Services.interfaces.IWorkType;
import com.siga.gestionprojet.dao.entities.WorkType;
import com.siga.gestionprojet.dto.DataAccessApi;
import com.siga.gestionprojet.dto.*;
import com.siga.gestionprojet.exception.WorkTypeNotFoundException;
import com.siga.gestionprojet.mapper.ProjectModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorkTypeService implements IWorkType {

    private DataAccessApi dataAccessApi;


    private ProjectModelMapper projectModelMapper;

    @Override
    public WorkType create(String name, String description) {
        WorkType workType = createOrUpdate(null, name, description);
        return workType;
    }

    @Override
    public List<WorkType> findAll() {
        List<WorkTypeDTO> workTypeDTOs = dataAccessApi.findAllWorkTypes();
        return workTypeDTOs.stream().map(w -> projectModelMapper.map(w, WorkType.class)).collect(Collectors.toList());
    }

    @Override
    public Optional<WorkType> findById(Integer workTypeId) {
        Optional<WorkTypeDTO> workTypeDTO = dataAccessApi.findWorkTypeById(workTypeId);
        return workTypeDTO.map(this::map);
    }

    @Override
    public WorkType update(Integer workTypeId, String name, String description) {
        dataAccessApi.findWorkTypeById(workTypeId).orElseThrow(() -> new WorkTypeNotFoundException(workTypeId));
        WorkType workType = createOrUpdate(workTypeId, name, description);
        return workType;
    }

    @Override
    public void deleteById(Integer workTypeId) {
        Assert.notNull(workTypeId, "work type id cannot be null");
        dataAccessApi.findWorkTypeById(workTypeId).orElseThrow(() -> new WorkTypeNotFoundException(workTypeId));
        dataAccessApi.deleteWorkTypeById(workTypeId);
    }

    private WorkType createOrUpdate(Integer workTypeId, String name, String description) {
        WorkTypeDTO workTypeDTO = new WorkTypeDTO();
        if (workTypeId != null) {
            workTypeDTO.setId(workTypeId);
        }
        workTypeDTO.setName(name);
        workTypeDTO.setDescription(description);
        workTypeDTO = dataAccessApi.createOrUpdateWorkType(workTypeDTO);
        return map(workTypeDTO);
    }

    private WorkType map(WorkTypeDTO workTypeDTO) {
        return projectModelMapper.map(workTypeDTO, WorkType.class);
    }


}

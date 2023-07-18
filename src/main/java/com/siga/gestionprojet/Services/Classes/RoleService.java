package com.siga.gestionprojet.Services.Classes;

import com.siga.gestionprojet.Services.interfaces.IRole;
import com.siga.gestionprojet.dao.entities.Role;
import com.siga.gestionprojet.dao.repositories.RoleRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleService implements IRole {

    private RoleRepo roleRepository;
    @Override
    public Role add(Role a) {
        return roleRepository.save(a);
    }
}
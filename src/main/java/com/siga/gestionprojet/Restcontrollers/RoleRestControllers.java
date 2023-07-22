package com.siga.gestionprojet.Restcontrollers;
import com.siga.gestionprojet.Services.interfaces.IRole;
import com.siga.gestionprojet.dao.entities.Role;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RoleRestControllers {
    private IRole iRole;
    @PostMapping("/ajouterRole")
    public Role ajouter(@RequestBody Role role)
    {
        return iRole.add(role);
    }
}
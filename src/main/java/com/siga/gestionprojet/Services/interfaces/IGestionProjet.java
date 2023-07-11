package com.siga.gestionprojet.Services.interfaces;

import com.siga.gestionprojet.dao.entities.User;

import java.util.List;

public interface IGestionProjet {
    User getUser(String username);
    List<User> getUsers();
    public String getCurrentUserName();
}

package com.siga.gestionprojet.Services.interfaces;

import com.siga.gestionprojet.dao.entities.User;

import java.util.List;

public interface IUser {
    void add(User a);
    User edit(User a);
    List<User> selectAll();
    User SelectById(long id);
    void deleteById(long id);
    User getUserByEmail(String email);
    User getUserById(long id);
    //List<User> searchUser(String query);
}
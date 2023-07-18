package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE User a " + "SET a.password = ?1    WHERE a.email = ?2")
    void resetPassword(String password,String email);

    @Query("SELECT COUNT(u.cin) FROM User u")
    int countAllUsers();
    Optional<User> findById(int id);
    User findByUsername(String username);
}

package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.Projet;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetRepo extends JpaRepository<Projet,Integer> {

    @EntityGraph(value = "Project.workTypes", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT p FROM Projet p WHERE p.id = :projectId")
    Optional<Projet> findWithWorkTypesFetchedById(@Param("projectId") Integer projectId);

    @EntityGraph(value = "Project.workTypes", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT p FROM Projet p")
    List<Projet> findAllWithWorkTypesFetched();

    @EntityGraph(value = "Project.projectAssignmentsWithProjectRoles", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT p FROM Projet p WHERE p.id = :projectId")
    Optional<Projet> findWithAssignmentsAndProjectRolesFetchedById(@Param("projectId") Integer projectId);

    @EntityGraph(value = "Projet.workTypes", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT p FROM Projet p JOIN p.projectAssignments a " +
            "WHERE a.validFrom <= :date AND " +
            "(a.validTo IS NULL OR :date <= a.validTo) AND " +
            "a.user.idUser = :userId")
    List<Projet> findAllAssignedProjectsWhereValidTimeOverlapsByUserId(@Param("date") LocalDate date, @Param("userId") Integer userId);
}

package com.siga.gestionprojet.dao.repositories;

import com.siga.gestionprojet.dao.entities.ProjectAssignment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProjectAssignmentRepo extends JpaRepository<ProjectAssignment, Integer> {

    @EntityGraph(value = "ProjectAssignment.projectRoles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<ProjectAssignment> findById(@Param("id") Integer id);

    @EntityGraph(value = "ProjectAssignment.projectRoles", type = EntityGraph.EntityGraphType.LOAD)
    List<ProjectAssignment> findAllByProjectIdAndUserIdUser(Integer projectId, Integer userId);
}

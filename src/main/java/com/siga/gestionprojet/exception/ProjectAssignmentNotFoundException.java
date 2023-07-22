package com.siga.gestionprojet.exception;

public class ProjectAssignmentNotFoundException extends RuntimeException {

    public ProjectAssignmentNotFoundException(Integer id) {
        this("Project assignment with id = " + id + " not found");
    }

    public ProjectAssignmentNotFoundException(String message) {
        super(message);
    }
}

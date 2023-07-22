package com.siga.gestionprojet.exception;

public class WorkTypeNotFoundException extends RuntimeException {

    public WorkTypeNotFoundException(Integer id) {
        this("Work type with id = " + id + " not found");
    }

    public WorkTypeNotFoundException(String message) {
        super(message);
    }
}

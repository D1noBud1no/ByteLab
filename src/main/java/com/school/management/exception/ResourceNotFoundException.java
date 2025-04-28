package com.school.management.exception;

/**
 * Eccezione personalizzata per risorse non trovate (404).
 */
public class ResourceNotFoundException extends RuntimeException {

    // Costruttore con messaggio personalizzato
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Costruttore con messaggio e causa
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

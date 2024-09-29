package com.api.picoplaca.Excepcciones;

public class RestriccionNotFoundException extends RuntimeException {
    public RestriccionNotFoundException(Long id) {
        super("No se encontró la restricción con ID: " + id);
    }
}

package com.api.picoplaca.Excepcciones;

public class HorarioRestriccionNotFoundException extends RuntimeException {
    public HorarioRestriccionNotFoundException(Long id) {
        super("No se encontró el horario con ID: " + id);
    }
}

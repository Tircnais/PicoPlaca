package com.api.picoplaca.Excepcciones;

public class VehiculoNotFoundException extends RuntimeException {
    public VehiculoNotFoundException(Long id) {
        super("No se encontró el carro con ID: " + id);
    }
}

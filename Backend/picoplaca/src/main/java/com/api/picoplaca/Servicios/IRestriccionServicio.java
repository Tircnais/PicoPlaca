package com.api.picoplaca.Servicios;

import com.api.picoplaca.Entidades.Restricciones;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IRestriccionServicio {
    public List<Restricciones> getAllRestricciones();
    public Restricciones createRestriccionModel(Restricciones restriccion);
    public Optional<Restricciones> GetRestriccionById(Long userId);
    public Restricciones UpdateRestriccionById(Long userId, Restricciones newRestriccion);
    public HashMap<String, String> DeleteRestriccionById(Long userId);
}
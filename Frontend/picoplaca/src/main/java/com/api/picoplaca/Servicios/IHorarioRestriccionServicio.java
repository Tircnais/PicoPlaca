package com.api.picoplaca.Servicios;

import com.api.picoplaca.Entidades.HorariosRestriccion;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IHorarioRestriccionServicio {
    public List<HorariosRestriccion> getAllHorarios();
    public HorariosRestriccion createHorarioModel(HorariosRestriccion horario);
    public Optional<HorariosRestriccion> GetHorarioById(Long userId);
    public HorariosRestriccion UpdateHorarioById(Long userId, HorariosRestriccion newHorario);
    public HashMap<String, String> DeleteHorarioById(Long userId);
}
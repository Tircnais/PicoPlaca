package com.api.picoplaca.Servicios;

import com.api.picoplaca.Entidades.Vehiculo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IVehiculoServicio {
    // Lista de vehiculos
    public List<Vehiculo> getAllVehiculos();
    public Vehiculo createVehiculoModel(Vehiculo carro);
    public Optional<Vehiculo> GetVehiculoById(Long userId);
    public Vehiculo UpdateVehiculoById(Long userId, Vehiculo newCarro);
    public HashMap<String, String> DeleteVehiculoById(Long userId);
}
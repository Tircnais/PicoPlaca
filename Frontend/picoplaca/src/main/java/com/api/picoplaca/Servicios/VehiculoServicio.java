package com.api.picoplaca.Servicios;

import com.api.picoplaca.Entidades.Vehiculo;
import com.api.picoplaca.Repositorio.VehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServicio {
    @Autowired
    VehiculoRepositorio objVehRepo;

    // Lista vehiculos
    public List<Vehiculo> getVehiculos(){
        return objVehRepo.findAll();
    }

    // Trae vehiculo por ID
    public Optional<Vehiculo> getVehiculo(Long id){
        return objVehRepo.findById(id);
    }

    // Guarda o actualiza
    public void saveOrUpdate(Vehiculo carro){
        objVehRepo.save(carro);
    }

    // Borrar por ID
    public void delete(Long id){
        objVehRepo.deleteById(id);
    }
}

package com.api.picoplaca.Controlador;

import com.api.picoplaca.Entidades.Vehiculo;
import com.api.picoplaca.Servicios.VehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Vehiculo")
public class VehiculoControler {
    @Autowired
    private VehiculoServicio objVehSs;

    @GetMapping
    public List<Vehiculo> getAllVehiculos(){
        return objVehSs.getVehiculos();
    }

    @GetMapping("/{carroId}")
    public Optional<Vehiculo> getVehiculo(@PathVariable("carroId") Long id){
        return objVehSs.getVehiculo(id);
    }

    @PostMapping
    public void saveUpdateVehiculo(@RequestBody Vehiculo carro){
        objVehSs.saveOrUpdate(carro);
    }

    @DeleteMapping("/{carroId}")
    public void deleteVehiculo(@PathVariable("carroId") Long id){
        objVehSs.delete(id);
    }
}

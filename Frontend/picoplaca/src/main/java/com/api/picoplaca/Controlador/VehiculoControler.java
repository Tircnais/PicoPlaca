package com.api.picoplaca.Controlador;

import com.api.picoplaca.Entidades.Vehiculo;
import com.api.picoplaca.Excepcciones.VehiculoNotFoundException;
import com.api.picoplaca.Servicios.IVehiculoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Vehiculo")
public class VehiculoControler {
    @Autowired
    private IVehiculoServicio carroServicio;

    @GetMapping("/Lista")
    public ResponseEntity<List<Vehiculo>> getAllVehiculos() {
        try {
            List<Vehiculo> Vehiculos = carroServicio.getAllVehiculos();
            return new ResponseEntity<>(Vehiculos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Nuevo regisro
    @PostMapping("/nuevo")
    public ResponseEntity<Vehiculo> createVehiculo(@RequestBody Vehiculo Vehiculo) {
        try {
            Vehiculo nuevoVehiculo = carroServicio.createVehiculoModel(Vehiculo);
            return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trae registro especificado
    @GetMapping("/get/{id}")
    public ResponseEntity<Vehiculo> getVehiculoById(@PathVariable Long id) {
        try {
            Vehiculo carro = carroServicio.GetVehiculoById(id).orElseThrow(() -> new VehiculoNotFoundException(id));
            return new ResponseEntity<>(carro, HttpStatus.OK);
        } catch (VehiculoNotFoundException e) {
            throw e; // Se maneja en el GlobalExceptionHandler
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualiza parte del registro el registro
    @PutMapping("/update/{id}")
    public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable Long id, @RequestBody Vehiculo VehiculoRequest) {
        try {
            Vehiculo updatedCarro = carroServicio.UpdateVehiculoById(id, VehiculoRequest);
            return new ResponseEntity<>(updatedCarro, HttpStatus.OK);
        } catch (VehiculoNotFoundException e) {
            throw e; // Se maneja en el GlobalExceptionHandler
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, String>> deleteVehiculo(@PathVariable Long id) {
        try {
            HashMap<String, String> response = carroServicio.DeleteVehiculoById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

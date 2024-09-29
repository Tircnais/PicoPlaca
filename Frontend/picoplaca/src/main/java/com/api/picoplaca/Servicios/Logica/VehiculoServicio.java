package com.api.picoplaca.Servicios.Logica;

import com.api.picoplaca.Entidades.Vehiculo;
import com.api.picoplaca.Repositorio.VehiculoRepositorio;
import com.api.picoplaca.Servicios.IVehiculoServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServicio implements IVehiculoServicio {
    private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoServicio.class);

    @Autowired
    private VehiculoRepositorio carroRepo;

    // Lista de vehiculos
    @Override
    public List<Vehiculo> getAllVehiculos() {
        try {
            return carroRepo.findAll();
            // return(ArrayList<CarroModel>) carroRepo.findAll();
        } catch (Exception e) {
            LOGGER.error("Error mienstras se consulta los vehiculos: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }

    @Override
    public Vehiculo createVehiculoModel(Vehiculo Carro) {
        return carroRepo.save(Carro);
    }

    // obtiene un registro especifico
    @Override
    public Optional<Vehiculo> GetVehiculoById(Long id) {
        try {
            return carroRepo.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error mientras se consulta un Carro por ID: {}", e.getMessage());
            throw new RuntimeException("Error consultar el Carro por ID");
        }
    }

    // actualiza un registro especifico
    @Override
    public Vehiculo UpdateVehiculoById(Long userId, Vehiculo CarroRequest) {
        try {
            Vehiculo exitsCarro = carroRepo.findById(userId).orElse(null);
            if (exitsCarro != null) {
                exitsCarro.setPlaca(CarroRequest.getPlaca());
                return carroRepo.save(exitsCarro);
            }
            throw new RuntimeException("Carro no encontrado");
        } catch (Exception e) {
            LOGGER.error("Error mientras se actulizaba el Carro: {}", e.getMessage());
            throw new RuntimeException("Error actualizando el Carro");
        }
    }

    @Override
    public HashMap<String, String> DeleteVehiculoById(Long userId) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Carro borrado con exitoso");
            carroRepo.deleteById(userId);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error mientras se elimina el Carro: {}", e.getMessage());
            throw new RuntimeException("Error eliminando el carro");
        }
    }
}

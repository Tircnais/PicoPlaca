package com.api.picoplaca.Servicios.Logica;

import com.api.picoplaca.Entidades.Restricciones;
import com.api.picoplaca.Repositorio.RestriccionRepositorio;
import com.api.picoplaca.Servicios.IRestriccionServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RestriccionesServicio implements IRestriccionServicio {
    private static final Logger LOGGER = LoggerFactory.getLogger(RestriccionesServicio.class);

    @Autowired
    private RestriccionRepositorio objRestRepo;

    // Lista de Restriccions
    @Override
    public List<Restricciones> getAllRestricciones() {
        try {
            return objRestRepo.findAll();
            // return(ArrayList<RestriccionModel>) objRestRepo.findAll();
        } catch (Exception e) {
            LOGGER.error("Error mienstras se consulta los Restriccions: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }

    @Override
    public Restricciones createRestriccionModel(Restricciones Restriccion) {
        return objRestRepo.save(Restriccion);
    }

    // obtiene un registro especifico
    @Override
    public Optional<Restricciones> GetRestriccionById(Long id) {
        try {
            return objRestRepo.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error mientras se consulta un Restriccion por ID: {}", e.getMessage());
            throw new RuntimeException("Error consultar el Restriccion por ID");
        }
    }

    // actualiza un registro especifico
    @Override
    public Restricciones UpdateRestriccionById(Long userId, Restricciones RestriccionRequest) {
        try {
            Restricciones exitsRestriccion = objRestRepo.findById(userId).orElse(null);
            if (exitsRestriccion != null) {
                exitsRestriccion.setDia(RestriccionRequest.getDia());
                exitsRestriccion.setUltimo_digito(RestriccionRequest.getUltimo_digito());
                return objRestRepo.save(exitsRestriccion);
            }
            throw new RuntimeException("Restriccion no encontrado");
        } catch (Exception e) {
            LOGGER.error("Error mientras se actulizaba el Restriccion: {}", e.getMessage());
            throw new RuntimeException("Error actualizando el Restriccion");
        }
    }

    @Override
    public HashMap<String, String> DeleteRestriccionById(Long userId) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Restriccion borrado con exitoso");
            objRestRepo.deleteById(userId);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error mientras se elimina el Restriccion: {}", e.getMessage());
            throw new RuntimeException("Error eliminando el Restriccion");
        }
    }
}

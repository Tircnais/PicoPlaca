package com.api.picoplaca.Servicios.Logica;

import com.api.picoplaca.Entidades.HorariosRestriccion;
import com.api.picoplaca.Repositorio.HorariosRestriccionRepositorio;
import com.api.picoplaca.Servicios.IHorarioRestriccionServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class HorariosRestriccionServicio implements IHorarioRestriccionServicio {
    private static final Logger LOGGER = LoggerFactory.getLogger(HorariosRestriccionServicio.class);
    @Autowired
    private HorariosRestriccionRepositorio objHorarioRepo;

    // Lista HorariosRestriccions
    @Override
    public List<HorariosRestriccion> getAllHorarios() {
        try {
            return objHorarioRepo.findAll();
        } catch (Exception e) {
            LOGGER.error("Error mienstras se consulta los Horarios: {}", e.getMessage());
            throw new RuntimeException("Error fetching users");
        }
    }

    // Nuevo registro
    @Override
    public HorariosRestriccion createHorarioModel(HorariosRestriccion Horario) {
        return objHorarioRepo.save(Horario);
    }

    // Trae HorariosRestriccion por ID
    @Override
    public Optional<HorariosRestriccion> GetHorarioById(Long id) {
        try {
            return objHorarioRepo.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error mientras se consulta un horario por ID: {}", e.getMessage());
            throw new RuntimeException("Error consultar el horario por ID");
        }
    }

    // Guarda o actualiza
    public void saveOrUpdate(HorariosRestriccion carro){
        objHorarioRepo.save(carro);
    }

    // Borrar por ID
    public void delete(Long id){
        objHorarioRepo.deleteById(id);
    }

    // actualiza un registro especifico
    @Override
    public HorariosRestriccion UpdateHorarioById(Long userId, HorariosRestriccion HorarioRequest) {
        try {
            HorariosRestriccion exitsHorario = objHorarioRepo.findById(userId).orElse(null);
            if (exitsHorario != null) {
                exitsHorario.setTipo(HorarioRequest.getTipo());
                exitsHorario.setHora_inicio(HorarioRequest.getHora_inicio());
                exitsHorario.setHora_fin(HorarioRequest.getHora_fin());
                return objHorarioRepo.save(exitsHorario);
            }
            throw new RuntimeException("Horario no encontrado");
        } catch (Exception e) {
            LOGGER.error("Error mientras se actulizaba el Horario: {}", e.getMessage());
            throw new RuntimeException("Error actualizando el Horario");
        }
    }
    
    @Override
    public HashMap<String, String> DeleteHorarioById(Long userId) {
        try {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Horario borrado con exitoso");
            objHorarioRepo.deleteById(userId);
            return response;
        } catch (Exception e) {
            LOGGER.error("Error mientras se elimina el Horario: {}", e.getMessage());
            throw new RuntimeException("Error eliminando el Horario");
        }
    }
}

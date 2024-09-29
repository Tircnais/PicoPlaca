package com.api.picoplaca.Servicios;

import com.api.picoplaca.Entidades.HorariosRestriccion;
import com.api.picoplaca.Repositorio.HorariosRestriccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorariosRestriccionServicio {
    @Autowired
    HorariosRestriccionRepositorio objHorarioRepo;

    // Lista HorariosRestriccions
    public List<HorariosRestriccion> getHorariosRestriccions(){
        return objHorarioRepo.findAll();
    }

    // Trae HorariosRestriccion por ID
    public Optional<HorariosRestriccion> getHorariosRestriccion(Long id){
        return objHorarioRepo.findById(id);
    }

    // Guarda o actualiza
    public void saveOrUpdate(HorariosRestriccion carro){
        objHorarioRepo.save(carro);
    }

    // Borrar por ID
    public void delete(Long id){
        objHorarioRepo.deleteById(id);
    }
}

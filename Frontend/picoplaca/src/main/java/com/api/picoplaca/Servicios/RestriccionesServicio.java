package com.api.picoplaca.Servicios;

import com.api.picoplaca.Entidades.Restricciones;
import com.api.picoplaca.Repositorio.RestriccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestriccionesServicio {
    @Autowired
    RestriccionRepositorio objRestRepo;

    // Lista Restriccioness
    public List<Restricciones> getRestriccioness(){
        return objRestRepo.findAll();
    }

    // Trae Restricciones por ID
    public Optional<Restricciones> getRestricciones(Long id){
        return objRestRepo.findById(id);
    }

    // Guarda o actualiza
    public void saveOrUpdate(Restricciones carro){
        objRestRepo.save(carro);
    }

    // Borrar por ID
    public void delete(Long id){
        objRestRepo.deleteById(id);
    }
}

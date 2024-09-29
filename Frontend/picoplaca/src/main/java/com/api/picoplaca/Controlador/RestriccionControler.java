package com.api.picoplaca.Controlador;

import com.api.picoplaca.Entidades.Restricciones;
import com.api.picoplaca.Servicios.RestriccionesServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/Restriccion")
public class RestriccionControler {
    @Autowired
    private RestriccionesServicio objRestSs;

    @GetMapping
    public List<Restricciones> getAllRestricciones(){
        return objRestSs.getRestriccioness();
    }

    @GetMapping("/{Id}")
    public Optional<Restricciones> getRestricciones(@PathVariable("Id") Long id){
        return objRestSs.getRestricciones(id);
    }

    @PostMapping
    public void saveUpdateRestriccion(@RequestBody Restricciones carro){
        objRestSs.saveOrUpdate(carro);
    }

    @DeleteMapping("/{Id}")
    public void deleteRestriccion(@PathVariable("Id") Long id){
        objRestSs.delete(id);
    }
}
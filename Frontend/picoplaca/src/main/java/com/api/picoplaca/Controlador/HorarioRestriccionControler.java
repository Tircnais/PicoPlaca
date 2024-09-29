package com.api.picoplaca.Controlador;

import com.api.picoplaca.Entidades.HorariosRestriccion;
import com.api.picoplaca.Servicios.HorariosRestriccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/HorarioRestriccion")
public class HorarioRestriccionControler {
    @Autowired
    private HorariosRestriccionServicio objVehSs;

    @GetMapping
    public List<HorariosRestriccion> getAllHorarios(){
        return objVehSs.getHorariosRestriccions();
    }

    @GetMapping("/{Id}")
    public Optional<HorariosRestriccion> getHorarioRestriccion(@PathVariable("Id") Long id){
        return objVehSs.getHorariosRestriccion(id);
    }

    @PostMapping
    public void saveUpdateHorario(@RequestBody HorariosRestriccion horario){
        objVehSs.saveOrUpdate(horario);
    }

    @DeleteMapping("/{Id}")
    public void deleteHorario(@PathVariable("Id") Long id){
        objVehSs.delete(id);
    }
}

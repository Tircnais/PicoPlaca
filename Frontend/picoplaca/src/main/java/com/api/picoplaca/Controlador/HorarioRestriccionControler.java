package com.api.picoplaca.Controlador;

import com.api.picoplaca.Entidades.HorariosRestriccion;
import com.api.picoplaca.Excepcciones.HorarioRestriccionNotFoundException;
import com.api.picoplaca.Servicios.IHorarioRestriccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/HorarioRestriccion")
public class HorarioRestriccionControler {
    @Autowired
    private IHorarioRestriccionServicio objHorarioSs;

    @GetMapping("/Lista")
    public ResponseEntity<List<HorariosRestriccion>> getAllHorarios() {
        try {
            List<HorariosRestriccion> cursos = objHorarioSs.getAllHorarios();
            return new ResponseEntity<>(cursos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<HorariosRestriccion> createHorario(@RequestBody HorariosRestriccion curso) {
        try {
            HorariosRestriccion nuevoCurso = objHorarioSs.createHorarioModel(curso);
            return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trae registro especificado
    @GetMapping("/{id}")
    public ResponseEntity<HorariosRestriccion> getHorarioById(@PathVariable Long id) {
        try {
            HorariosRestriccion curso = objHorarioSs.GetHorarioById(id).orElseThrow(() -> new HorarioRestriccionNotFoundException(id));
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } catch (HorarioRestriccionNotFoundException e) {
            throw e; // Se maneja en el GlobalExceptionHandler
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualiza parte del registro el registro
    @PutMapping("/update/{id}")
    public ResponseEntity<HorariosRestriccion> updateHorario(@PathVariable Long id, @RequestBody HorariosRestriccion cursoRequest) {
        try {
            HorariosRestriccion updatedCurso = objHorarioSs.UpdateHorarioById(id, cursoRequest);
            return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
        } catch (HorarioRestriccionNotFoundException e) {
            throw e; // Se maneja en el GlobalExceptionHandler
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, String>> deleteHorario(@PathVariable Long id) {
        try {
            HashMap<String, String> response = objHorarioSs.DeleteHorarioById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

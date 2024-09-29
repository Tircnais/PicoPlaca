package com.api.picoplaca.Controlador;

import com.api.picoplaca.Entidades.Restricciones;
import com.api.picoplaca.Excepcciones.RestriccionNotFoundException;
import com.api.picoplaca.Servicios.IRestriccionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/Restriccion")
public class RestriccionControler {
    @Autowired
    private IRestriccionServicio objRestSs;

    @GetMapping("/Lista")
    public ResponseEntity<List<Restricciones>> getAllRestricciones() {
        try {
            List<Restricciones> cursos = objRestSs.getAllRestricciones();
            return new ResponseEntity<>(cursos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/nuevo")
    public ResponseEntity<Restricciones> createRestriccion(@RequestBody Restricciones curso) {
        try {
            Restricciones nuevoCurso = objRestSs.createRestriccionModel(curso);
            return new ResponseEntity<>(nuevoCurso, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Trae registro especificado
    @GetMapping("/get/{id}")
    public ResponseEntity<Restricciones> getRestriccionById(@PathVariable Long id) {
        try {
            Restricciones curso = objRestSs.GetRestriccionById(id).orElseThrow(() -> new RestriccionNotFoundException(id));
            return new ResponseEntity<>(curso, HttpStatus.OK);
        } catch (RestriccionNotFoundException e) {
            throw e; // Se maneja en el GlobalExceptionHandler
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Actualiza parte del registro el registro
    @PutMapping("/update/{id}")
    public ResponseEntity<Restricciones> updateRestriccion(@PathVariable Long id, @RequestBody Restricciones cursoRequest) {
        try {
            Restricciones updatedCurso = objRestSs.UpdateRestriccionById(id, cursoRequest);
            return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
        } catch (RestriccionNotFoundException e) {
            throw e; // Se maneja en el GlobalExceptionHandler
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HashMap<String, String>> deleteRestriccion(@PathVariable Long id) {
        try {
            HashMap<String, String> response = objRestSs.DeleteRestriccionById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
package com.api.picoplaca.Repositorio;

import com.api.picoplaca.Entidades.Restricciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestriccionRepositorio extends JpaRepository<Restricciones, Long> {
    
}

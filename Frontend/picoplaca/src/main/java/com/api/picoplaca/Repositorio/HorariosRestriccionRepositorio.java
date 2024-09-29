package com.api.picoplaca.Repositorio;

import com.api.picoplaca.Entidades.HorariosRestriccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HorariosRestriccionRepositorio extends JpaRepository<HorariosRestriccion, Long> {
    
}

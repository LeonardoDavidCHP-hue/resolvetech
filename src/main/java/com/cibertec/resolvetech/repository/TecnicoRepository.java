package com.cibertec.resolvetech.repository;

import com.cibertec.resolvetech.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    List<Tecnico> findByEspecialidadContainingIgnoreCase(String especialidad);
}

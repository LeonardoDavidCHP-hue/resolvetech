package com.cibertec.resolvetech.repository;

import com.cibertec.resolvetech.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {

    Optional<Usuario> findByNombre(String nombre);
}

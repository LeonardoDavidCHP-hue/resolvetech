package com.cibertec.resolvetech.security.dto;

import java.time.LocalDateTime;

public record UsuarioResponseDto(
        Long id,
        String nombre,
        String rol,
        boolean activo,
        LocalDateTime fechaCreacion
) {
}

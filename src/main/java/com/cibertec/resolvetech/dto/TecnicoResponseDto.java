package com.cibertec.resolvetech.dto;

public record TecnicoResponseDto(
        Long id,
        String nombre,
        String rol,
        boolean activo,
        String especialidad,
        String telefono,
        SedeResponseDto sede
) {
}

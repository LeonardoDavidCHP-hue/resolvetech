package com.cibertec.resolvetech.security.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "La contraseña es obligatoria")
        String password
) {
}

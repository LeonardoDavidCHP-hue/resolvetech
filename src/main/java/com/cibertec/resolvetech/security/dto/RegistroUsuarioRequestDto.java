package com.cibertec.resolvetech.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroUsuarioRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 80, message = "El nombre no debe superar 80 caracteres")
        String nombre,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, max = 100, message = "La contraseña debe tener entre 6 y 100 caracteres")
        String password
) {
}

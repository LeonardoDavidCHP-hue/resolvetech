package com.cibertec.resolvetech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TecnicoRequestDto(
        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 80, message = "El nombre no debe superar 80 caracteres")
        String nombre,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(max = 100, message = "La contraseña no debe superar 100 caracteres")
        String password,

        @NotBlank(message = "La especialidad es obligatoria")
        @Size(max = 50, message = "La especialidad no debe superar 50 caracteres")
        String especialidad,

        @Size(max = 20, message = "El teléfono no debe superar 20 caracteres")
        String telefono,

        @NotNull(message = "Debe seleccionar una sede")
        Long idSede
) {
}

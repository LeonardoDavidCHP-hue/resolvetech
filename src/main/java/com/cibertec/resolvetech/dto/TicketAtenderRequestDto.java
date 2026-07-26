package com.cibertec.resolvetech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketAtenderRequestDto(
        @NotNull(message = "Debe seleccionar el nuevo estado")
        Long idEstado,

        Long idTecnico,

        @NotBlank(message = "La observacion es obligatoria")
        @Size(max = 300, message = "La observacion no debe superar 300 caracteres")
        String observacionSoporte,

        @NotNull(message = "Debe indicar el usuario que realiza la atencion")
        Long idUsuario
) {
}

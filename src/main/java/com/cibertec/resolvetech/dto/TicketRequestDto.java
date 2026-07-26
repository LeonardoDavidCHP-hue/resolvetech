package com.cibertec.resolvetech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TicketRequestDto(
        @NotNull(message = "Debe seleccionar una categoria")
        Long idCategoria,

        @NotNull(message = "Debe seleccionar una sede")
        Long idSede,

        @NotBlank(message = "La descripcion es obligatoria")
        @Size(max = 300, message = "La descripcion no debe superar 300 caracteres")
        String descripcion,

        @NotBlank(message = "La prioridad es obligatoria")
        String prioridad,

        // Aqui estoy dejando que el tecnico no sea obligatorio al crear ticket, solo por si las moscas
        Long idTecnico,

        @NotNull(message = "Debe indicar el usuario que registra el ticket")
        Long idUsuarioCreador
) {
}

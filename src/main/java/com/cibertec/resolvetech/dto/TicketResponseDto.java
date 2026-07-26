package com.cibertec.resolvetech.dto;

import java.time.LocalDateTime;

public record TicketResponseDto(
        Long id,
        String numeroTicket,
        LocalDateTime fechaCreacion,
        CategoriaResponseDto categoria,
        SedeResponseDto sede,
        String descripcion,
        String prioridad,
        EstadoResponseDto estado,
        String observacionSoporte,
        LocalDateTime fechaAtencion,
        String tecnicoNombre,
        String usuarioCreadorNombre,
        Long version
) {
}

package com.cibertec.resolvetech.service;

import com.cibertec.resolvetech.dto.TicketAtenderRequestDto;
import com.cibertec.resolvetech.dto.TicketRequestDto;
import com.cibertec.resolvetech.dto.TicketResponseDto;

import java.util.List;

public interface TicketService {

    List<TicketResponseDto> obtenerTodos();

    TicketResponseDto registrar(TicketRequestDto request);

    TicketResponseDto atender(Long id, TicketAtenderRequestDto request);

    void eliminar(Long id);
}

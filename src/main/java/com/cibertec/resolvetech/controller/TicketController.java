package com.cibertec.resolvetech.controller;

import com.cibertec.resolvetech.dto.TicketRequestDto;
import com.cibertec.resolvetech.dto.TicketResponseDto;
import com.cibertec.resolvetech.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cibertec.resolvetech.dto.TicketAtenderRequestDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(ticketService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<TicketResponseDto> registrar(
            @Valid @RequestBody TicketRequestDto request) {

        TicketResponseDto ticketCreado = ticketService.registrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ticketCreado);
    }


    @PutMapping("/{id}/atender")
    public ResponseEntity<TicketResponseDto> atender(
            @PathVariable Long id,
            @Valid @RequestBody TicketAtenderRequestDto request) {

        return ResponseEntity.ok(
                ticketService.atender(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ticketService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

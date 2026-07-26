package com.cibertec.resolvetech.controller;

import com.cibertec.resolvetech.dto.EstadoResponseDto;
import com.cibertec.resolvetech.service.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<List<EstadoResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(estadoService.obtenerTodos());
    }
}

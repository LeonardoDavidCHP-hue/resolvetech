package com.cibertec.resolvetech.controller;

import com.cibertec.resolvetech.dto.TecnicoRequestDto;
import com.cibertec.resolvetech.dto.TecnicoResponseDto;
import com.cibertec.resolvetech.service.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tecnicos")
public class TecnicoController {

    private final TecnicoService tecnicoService;

    public TecnicoController(TecnicoService tecnicoService) {
        this.tecnicoService = tecnicoService;
    }

    @GetMapping
    public ResponseEntity<List<TecnicoResponseDto>> obtenerTodos() {
        return ResponseEntity.ok(tecnicoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<TecnicoResponseDto> registrar(
            @Valid @RequestBody TecnicoRequestDto request) {

        TecnicoResponseDto tecnicoCreado = tecnicoService.registrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tecnicoCreado);
    }
}

package com.cibertec.resolvetech.controller;

import com.cibertec.resolvetech.dto.SedeResponseDto;
import com.cibertec.resolvetech.service.SedeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sedes")
public class SedeController {

    private final SedeService sedeService;

    public SedeController(SedeService sedeService) {
        this.sedeService = sedeService;
    }

    @GetMapping
    public ResponseEntity<List<SedeResponseDto>> obtenerTodas() {
        return ResponseEntity.ok(sedeService.obtenerTodas());
    }
}

package com.cibertec.resolvetech.controller;

import com.cibertec.resolvetech.dto.CategoriaResponseDto;
import com.cibertec.resolvetech.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDto>> obtenerTodas() {
        return ResponseEntity.ok(categoriaService.obtenerTodas());
    }
}

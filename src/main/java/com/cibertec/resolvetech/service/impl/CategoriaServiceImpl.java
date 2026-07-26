package com.cibertec.resolvetech.service.impl;

import com.cibertec.resolvetech.dto.CategoriaResponseDto;
import com.cibertec.resolvetech.mapper.CategoriaMapper;
import com.cibertec.resolvetech.repository.CategoriaRepository;
import com.cibertec.resolvetech.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    @Override
    public List<CategoriaResponseDto> obtenerTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toResponseDto)
                .toList();
    }
}

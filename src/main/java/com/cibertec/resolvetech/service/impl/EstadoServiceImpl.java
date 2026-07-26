package com.cibertec.resolvetech.service.impl;

import com.cibertec.resolvetech.dto.EstadoResponseDto;
import com.cibertec.resolvetech.mapper.EstadoMapper;
import com.cibertec.resolvetech.repository.EstadoRepository;
import com.cibertec.resolvetech.service.EstadoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl implements EstadoService{
    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    public EstadoServiceImpl(EstadoRepository estadoRepository, EstadoMapper estadoMapper) {
        this.estadoRepository = estadoRepository;
        this.estadoMapper = estadoMapper;
    }

    @Override
    public List<EstadoResponseDto> obtenerTodos() {
        return estadoRepository.findAll()
                .stream()
                .map(estadoMapper::toResponseDto)
                .toList();
    }
}

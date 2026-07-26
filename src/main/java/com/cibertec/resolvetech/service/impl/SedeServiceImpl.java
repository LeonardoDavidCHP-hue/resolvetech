package com.cibertec.resolvetech.service.impl;

import com.cibertec.resolvetech.dto.SedeResponseDto;
import com.cibertec.resolvetech.mapper.SedeMapper;
import com.cibertec.resolvetech.repository.SedeRepository;
import com.cibertec.resolvetech.service.SedeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SedeServiceImpl implements SedeService  {

    private final SedeRepository sedeRepository;
    private final SedeMapper sedeMapper;

    public SedeServiceImpl(SedeRepository sedeRepository, SedeMapper sedeMapper) {
        this.sedeRepository = sedeRepository;
        this.sedeMapper = sedeMapper;
    }

    @Override
    public List<SedeResponseDto> obtenerTodas() {
        return sedeRepository.findAll()
                .stream()
                .map(sedeMapper::toResponseDto)
                .toList();
    }
}

package com.cibertec.resolvetech.service.impl;

import com.cibertec.resolvetech.dto.TecnicoRequestDto;
import com.cibertec.resolvetech.dto.TecnicoResponseDto;
import com.cibertec.resolvetech.entity.Sede;
import com.cibertec.resolvetech.entity.Tecnico;
import com.cibertec.resolvetech.exception.RecursoNoEncontradoException;
import com.cibertec.resolvetech.mapper.TecnicoMapper;
import com.cibertec.resolvetech.repository.SedeRepository;
import com.cibertec.resolvetech.repository.TecnicoRepository;
import com.cibertec.resolvetech.service.TecnicoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
@Transactional
public class TecnicoServiceImpl implements TecnicoService {

    private final TecnicoRepository tecnicoRepository;
    private final SedeRepository sedeRepository;
    private final TecnicoMapper tecnicoMapper;
    private final PasswordEncoder passwordEncoder;

    public TecnicoServiceImpl(
            TecnicoRepository tecnicoRepository,
            SedeRepository sedeRepository,
            TecnicoMapper tecnicoMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.tecnicoRepository = tecnicoRepository;
        this.sedeRepository = sedeRepository;
        this.tecnicoMapper = tecnicoMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<TecnicoResponseDto> obtenerTodos() {
        return tecnicoRepository.findAll()
                .stream()
                .map(tecnicoMapper::toResponseDto)
                .toList();
    }

    @Override
    public TecnicoResponseDto registrar(TecnicoRequestDto request) {

        Sede sede = sedeRepository.findById(request.idSede())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("La sede seleccionada no existe"));

        Tecnico tecnico = new Tecnico(
                request.nombre(),
                passwordEncoder.encode(request.password()),
                "SOPORTE",
                request.especialidad(),
                request.telefono(),
                sede
        );

        Tecnico tecnicoGuardado = tecnicoRepository.save(tecnico);

        return tecnicoMapper.toResponseDto(tecnicoGuardado);
    }
}

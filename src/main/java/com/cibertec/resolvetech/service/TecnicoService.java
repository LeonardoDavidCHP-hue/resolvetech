package com.cibertec.resolvetech.service;

import com.cibertec.resolvetech.dto.TecnicoRequestDto;
import com.cibertec.resolvetech.dto.TecnicoResponseDto;

import java.util.List;

public interface TecnicoService {

    List<TecnicoResponseDto> obtenerTodos();

    TecnicoResponseDto registrar(TecnicoRequestDto request);
}

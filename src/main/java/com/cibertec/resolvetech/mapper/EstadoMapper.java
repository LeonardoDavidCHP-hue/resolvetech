package com.cibertec.resolvetech.mapper;

import com.cibertec.resolvetech.dto.EstadoResponseDto;
import com.cibertec.resolvetech.entity.Estado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoMapper {

    EstadoResponseDto toResponseDto(Estado estado);
}

package com.cibertec.resolvetech.mapper;

import com.cibertec.resolvetech.dto.SedeResponseDto;
import com.cibertec.resolvetech.entity.Sede;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SedeMapper {

    SedeResponseDto toResponseDto(Sede sede);
}

package com.cibertec.resolvetech.mapper;

import com.cibertec.resolvetech.dto.TecnicoResponseDto;
import com.cibertec.resolvetech.entity.Tecnico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SedeMapper.class})
public interface TecnicoMapper {

    TecnicoResponseDto toResponseDto(Tecnico tecnico);
}

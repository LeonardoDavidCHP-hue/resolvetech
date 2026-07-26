package com.cibertec.resolvetech.mapper;

import com.cibertec.resolvetech.dto.CategoriaResponseDto;
import com.cibertec.resolvetech.entity.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaResponseDto toResponseDto(Categoria categoria);
}

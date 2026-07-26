package com.cibertec.resolvetech.mapper;

import com.cibertec.resolvetech.dto.TicketResponseDto;
import com.cibertec.resolvetech.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class, EstadoMapper.class, SedeMapper.class})
public interface TicketMapper {

    @Mapping(target = "tecnicoNombre", expression = "java(ticket.getTecnico() != null ? ticket.getTecnico().getNombre() : null)")
    @Mapping(target = "usuarioCreadorNombre", source = "usuarioCreador.nombre")
    TicketResponseDto toResponseDto(Ticket ticket);
}

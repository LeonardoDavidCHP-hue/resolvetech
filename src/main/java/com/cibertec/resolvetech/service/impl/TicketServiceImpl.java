package com.cibertec.resolvetech.service.impl;

import com.cibertec.resolvetech.dto.TicketAtenderRequestDto;
import com.cibertec.resolvetech.dto.TicketRequestDto;
import com.cibertec.resolvetech.dto.TicketResponseDto;
import com.cibertec.resolvetech.entity.*;
import com.cibertec.resolvetech.exception.RecursoNoEncontradoException;
import com.cibertec.resolvetech.mapper.TicketMapper;
import com.cibertec.resolvetech.repository.*;
import com.cibertec.resolvetech.service.TicketService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final CategoriaRepository categoriaRepository;
    private final SedeRepository sedeRepository;
    private final EstadoRepository estadoRepository;
    private final TecnicoRepository tecnicoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TicketMapper ticketMapper;
    private final EntityManager entityManager;

    public TicketServiceImpl(
            TicketRepository ticketRepository,
            CategoriaRepository categoriaRepository,
            SedeRepository sedeRepository,
            EstadoRepository estadoRepository,
            TecnicoRepository tecnicoRepository,
            UsuarioRepository usuarioRepository,
            TicketMapper ticketMapper,
            EntityManager entityManager
    ) {
        this.ticketRepository = ticketRepository;
        this.categoriaRepository = categoriaRepository;
        this.sedeRepository = sedeRepository;
        this.estadoRepository = estadoRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.ticketMapper = ticketMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<TicketResponseDto> obtenerTodos() {
        return ticketRepository.listarTicketsConDetalle()
                .stream()
                .map(ticketMapper::toResponseDto)
                .toList();
    }

    @Override
    public TicketResponseDto registrar(TicketRequestDto request) {

        Categoria categoria = categoriaRepository.findById(request.idCategoria())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("La categoria seleccionada no existe"));

        Sede sede = sedeRepository.findById(request.idSede())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("La sede seleccionada no existe"));

        Estado estadoPendiente = estadoRepository.findById(1L)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("El estado inicial no existe"));

        Usuario usuarioCreador = usuarioRepository.findById(request.idUsuarioCreador())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("El usuario creador no existe"));

        Tecnico tecnico = null;
        if (request.idTecnico() != null) {
            tecnico = tecnicoRepository.findById(request.idTecnico())
                    .orElseThrow(() ->
                            new RecursoNoEncontradoException("El tecnico seleccionado no existe"));
        }

        Ticket ticket = Ticket.builder()
                .numeroTicket(generarNumeroTicket())
                .fechaCreacion(LocalDateTime.now())
                .categoria(categoria)
                .sede(sede)
                .descripcion(request.descripcion())
                .prioridad(request.prioridad())
                .estado(estadoPendiente)
                .tecnico(tecnico)
                .usuarioCreador(usuarioCreador)
                .build();

        entityManager.persist(ticket);

        entityManager.flush();

        HistorialTicket historialInicial = HistorialTicket.builder()
                .ticket(ticket)
                .estado(estadoPendiente)
                .fechaCambio(LocalDateTime.now())
                .observacion("Ticket registrado")
                .usuario(usuarioCreador)
                .build();

        entityManager.persist(historialInicial);
        entityManager.flush();

        return ticketMapper.toResponseDto(ticket);
    }

    @Override
    public TicketResponseDto atender(Long id, TicketAtenderRequestDto request) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Ticket no encontrado con ID: " + id));

        Estado nuevoEstado = estadoRepository.findById(request.idEstado())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("El estado seleccionado no existe"));

        Usuario usuarioAccion = usuarioRepository.findById(request.idUsuario())
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("El usuario indicado no existe"));

        if (request.idTecnico() != null) {
            Tecnico tecnico = tecnicoRepository.findById(request.idTecnico())
                    .orElseThrow(() ->
                            new RecursoNoEncontradoException("El tecnico seleccionado no existe"));
            ticket.setTecnico(tecnico);
        }

        ticket.setEstado(nuevoEstado);
        ticket.setObservacionSoporte(request.observacionSoporte());
        ticket.setFechaAtencion(LocalDateTime.now());

        entityManager.flush();

        HistorialTicket historial = HistorialTicket.builder()
                .ticket(ticket)
                .estado(nuevoEstado)
                .fechaCambio(LocalDateTime.now())
                .observacion(request.observacionSoporte())
                .usuario(usuarioAccion)
                .build();

        entityManager.persist(historial);
        entityManager.flush();

        return ticketMapper.toResponseDto(ticket);
    }

    private String generarNumeroTicket() {
        long total = ticketRepository.count() + 1;
        return String.format("RT%06d.26", total);
    }

    @Override
    public void eliminar(Long id) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Ticket no encontrado con ID: " + id));

        ticketRepository.delete(ticket);
    }

}

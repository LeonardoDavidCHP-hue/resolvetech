package com.cibertec.resolvetech.repository;

import com.cibertec.resolvetech.entity.Ticket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long>  {

    Optional<Ticket> findByNumeroTicket(String numeroTicket);

    @EntityGraph(attributePaths = {"categoria", "sede", "estado", "tecnico"})
    @Query("select t from Ticket t order by t.id asc")
    List<Ticket> listarTicketsConDetalle();

    @Query("select max(t.id) from Ticket t")
    Long obtenerMaxId();

    List<Ticket> findByEstado_NombreIgnoreCase(String nombreEstado);
}

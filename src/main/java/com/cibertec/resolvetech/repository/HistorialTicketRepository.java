package com.cibertec.resolvetech.repository;

import com.cibertec.resolvetech.entity.HistorialTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialTicketRepository extends JpaRepository<HistorialTicket, Long>  {

    List<HistorialTicket> findByTicket_IdOrderByFechaCambioAsc(Long ticketId);
}

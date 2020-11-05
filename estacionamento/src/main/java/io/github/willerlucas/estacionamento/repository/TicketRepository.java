package io.github.willerlucas.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.willerlucas.estacionamento.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

}

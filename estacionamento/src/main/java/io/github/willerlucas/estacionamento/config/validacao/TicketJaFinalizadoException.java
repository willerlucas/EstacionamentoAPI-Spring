package io.github.willerlucas.estacionamento.config.validacao;

import io.github.willerlucas.estacionamento.model.Ticket;

public class TicketJaFinalizadoException extends Exception {
	public TicketJaFinalizadoException(Ticket ticket) {
		System.out.println("O ticket já foi finalizado no dia " + ticket.getSaida() );
	}
}

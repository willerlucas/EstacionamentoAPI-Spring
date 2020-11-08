package io.github.willerlucas.estacionamento.config.validacao;

import io.github.willerlucas.estacionamento.model.Ticket;

@SuppressWarnings("serial")
public class VeiculoJaEstacionadoException extends Exception {

	public VeiculoJaEstacionadoException(String message) {
		super(message);
	}

}

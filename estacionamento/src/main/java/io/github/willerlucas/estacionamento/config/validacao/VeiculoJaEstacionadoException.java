package io.github.willerlucas.estacionamento.config.validacao;

import io.github.willerlucas.estacionamento.model.Ticket;

public class VeiculoJaEstacionadoException extends Exception {

	public VeiculoJaEstacionadoException(Ticket ticket) {
		System.out.println("O veículo já está estacionado");
	}

}

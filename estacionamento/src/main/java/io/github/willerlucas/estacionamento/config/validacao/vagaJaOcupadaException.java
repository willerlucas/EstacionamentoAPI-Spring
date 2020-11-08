package io.github.willerlucas.estacionamento.config.validacao;

import io.github.willerlucas.estacionamento.model.Vaga;

@SuppressWarnings("serial")
public class vagaJaOcupadaException extends Exception {
	public vagaJaOcupadaException (Vaga vaga) {
		System.out.println("A vaga " + vaga.getId() + "já está ocupada");
	}
}

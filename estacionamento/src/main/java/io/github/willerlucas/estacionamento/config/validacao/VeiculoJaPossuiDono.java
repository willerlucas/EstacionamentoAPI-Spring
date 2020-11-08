package io.github.willerlucas.estacionamento.config.validacao;

public class VeiculoJaPossuiDono extends Exception {
	public void VeiculoJaEstacionadoException() {
		System.out.println("Veiculo ja atribuido a um dono");
	}
}

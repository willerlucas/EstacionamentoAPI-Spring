package io.github.willerlucas.estacionamento.config.validacao;

@SuppressWarnings("serial")
public class VeiculoJaPossuiDono extends Exception {
	public void VeiculoJaEstacionadoException() {
		System.out.println("Veiculo ja atribuido a um dono");
	}
}

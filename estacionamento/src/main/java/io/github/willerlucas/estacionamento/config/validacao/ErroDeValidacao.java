package io.github.willerlucas.estacionamento.config.validacao;

public class ErroDeValidacao {
	
	private String campo;
	private String erro;
	
	public ErroDeValidacao(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	

}
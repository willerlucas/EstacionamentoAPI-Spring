package io.github.willerlucas.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_veiculo")
public class Veiculo {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	//@NotNull(message = "Por favor informe a placa do veiculo") 
	private String placa;
	
	@NotNull(message = "Por favor informe o modelo do veiculo") 
	private String modelo;
	
	@Enumerated(EnumType.STRING)
	private VeiculoTipo tipo;
	
	private boolean zeroKm = false;

	public Veiculo(){
		
	}
	
	public Veiculo(Veiculo veiculo) {
		this.Id = veiculo.Id;
		this.modelo = veiculo.modelo;
		this.tipo = veiculo.tipo;
		this.zeroKm = veiculo.isZeroKm();
		
		if(veiculo.isZeroKm()) {	
			veiculo.setPlaca("AAA-000");
		} else {
			veiculo.setPlaca(veiculo.placa);
		}
		
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public VeiculoTipo getTipo() {
		return tipo;
	}

	public void setTipo(VeiculoTipo tipo) {
		this.tipo = tipo;
	}

	public boolean isZeroKm() {
		return zeroKm;
	}

	public void setZeroKm(boolean zeroKm) {
		this.zeroKm = zeroKm;
	}
	
	
}

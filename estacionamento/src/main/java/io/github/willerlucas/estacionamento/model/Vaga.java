package io.github.willerlucas.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_vaga")
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private VagaStatus status;
	
	Vaga(){
		
	}
	
	public Vaga(Vaga vaga) {
		this.status = vaga.status;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VagaStatus getStatus() {
		return status;
	}

	public void setStatus(VagaStatus status) {
		this.status = status;
	}
	
	
	
}

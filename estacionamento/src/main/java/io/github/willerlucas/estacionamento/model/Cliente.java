package io.github.willerlucas.estacionamento.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="TB_CLIENTE")
public class Cliente {
	
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank 
	private String CPF;
	
	@NotBlank
	private String nome;
	
	private String email;
	
	@NotBlank
	private String telefone;
	
	@OneToMany
	private List<Veiculo> veiculos = new ArrayList<>();

}

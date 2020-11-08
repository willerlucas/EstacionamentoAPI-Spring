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
@Table(name = "tb_cliente")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank 
	private String CPF;
	
	@NotBlank
	private String nome;
	
	private String email;
	
	@NotBlank
	private String telefone;
	
	@OneToMany
	private List<Veiculo> veiculos = new ArrayList<>();

	Cliente(){
		
	}
	
	public Cliente(Cliente cliente) {
		this.id = cliente.id;
		this.CPF = cliente.CPF;
		this.email = cliente.email;
		this.nome = cliente.nome;
		this.telefone = cliente.telefone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Veiculo> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(List<Veiculo> veiculos) {
		this.veiculos = veiculos;
	}
	
	public void addVeiculo(Veiculo veiculo) {
		this.veiculos.add(veiculo);
	}

	public void rmvVeiculo(Veiculo veiculo) {
		this.veiculos.remove(veiculo);
		
	}
	
	
	
	

}

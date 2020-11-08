package io.github.willerlucas.estacionamento.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_ticket")
public class Ticket {
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "por favor informe o veiculo")
	@ManyToOne
	private Veiculo veiculo;

	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy-hh:mm")
	private LocalDateTime entrada;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy-hh:mm")
	private LocalDateTime saida;
	
	
	private int preco;
	
	@NotNull(message = "por favor informe o codigo da vaga")
	@ManyToOne
	private Vaga vaga;
	
	@Enumerated(EnumType.STRING)
	private TicketStatus status;
	
	public Ticket(){
		
	}
	
	public Ticket(Ticket ticket) {
		//this.id = ticket.id;
		this.entrada = LocalDateTime.now();
		this.veiculo = ticket.veiculo;
		this.status = TicketStatus.ABERTO;
		this.vaga = ticket.vaga;			
	}

	public Long getId() {
		return id;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public int getPreco() {
		return preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}
	
	

}

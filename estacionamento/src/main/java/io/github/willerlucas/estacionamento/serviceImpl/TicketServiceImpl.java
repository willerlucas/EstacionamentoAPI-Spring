package io.github.willerlucas.estacionamento.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.joda.time.Hours;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.model.TicketStatus;
import io.github.willerlucas.estacionamento.model.Vaga;
import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.repository.TicketRepository;
import io.github.willerlucas.estacionamento.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	private static final long PRECO_HORA = 3;

	private Veiculo veiculo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy-hh:mm")
	private LocalDateTime entrada = LocalDateTime.now();
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy-hh:mm")
	private LocalDateTime saida;

	private Vaga vaga;
	
	@Enumerated(EnumType.STRING)
	private TicketStatus status = TicketStatus.FINALIZADO;

	
	
	
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

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}

	public TicketStatus getStatus() {
		return status;
	}

	public void setStatus(TicketStatus status) {
		this.status = status;
	}

	public TicketRepository getTicketRepository() {
		return ticketRepository;
	}

	public void setTicketRepository(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	void TicketService(){
		
	}
	
//	public TicketServiceImpl(Ticket ticket) {
//		this.saida = ticket.getSaida();
//		this.status = ticket.getStatus();
//		this.vaga = ticket.getVaga();
//	}
//	
	
	
	@Autowired
	TicketRepository ticketRepository;

	@Override
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket findById(long id) {

		return ticketRepository.findById(id).get();
	}

	@Override
	public Ticket save(Ticket ticket) {

		//ticket.setEntrada(LocalDateTime.now());
		
		return ticketRepository.save(ticket);
		
	}

	@Override
	public List<Ticket> listaAbertos() {

		List<Ticket> tickets = ticketRepository.findAll();

		List<Ticket> ticketsAbertos = tickets.stream().filter(t -> t.getStatus() == TicketStatus.ABERTO)
				.collect(Collectors.toList());

		return ticketsAbertos;

	}
	
	@Override
	public List<Ticket> listaFechados() {

		List<Ticket> tickets = ticketRepository.findAll();

		List<Ticket> ticketsFechados = tickets.stream().filter(t -> t.getStatus() == TicketStatus.FINALIZADO)
				.collect(Collectors.toList());

		return ticketsFechados;

	}

	@Override
	public Ticket finalizar(Long id, TicketRepository ticketRepository) {
		
		
		Ticket ticket = ticketRepository.getOne(id);
		

		ticket.setSaida(LocalDateTime.now());
		ticket.setStatus(TicketStatus.FINALIZADO);
		
		//metodo calcular preço
		long valorTotal = calcularPreco(ticket.getSaida(), ticket.getEntrada());
		System.out.println("chegou aqui    "+valorTotal);
		ticket.setPreco((int) valorTotal);
		
		ticketRepository.save(ticket);
		
		
		return ticket;

	}

	private long calcularPreco(LocalDateTime saida, LocalDateTime entrada) {
				
		return ((Duration.between(entrada, saida).toHours()) * PRECO_HORA);
			 
	}

}

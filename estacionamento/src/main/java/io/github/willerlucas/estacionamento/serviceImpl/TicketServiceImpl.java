package io.github.willerlucas.estacionamento.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.github.willerlucas.estacionamento.config.validacao.TicketJaFinalizadoException;
import io.github.willerlucas.estacionamento.config.validacao.VeiculoJaEstacionadoException;
import io.github.willerlucas.estacionamento.config.validacao.vagaJaOcupadaException;
import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.model.TicketStatus;
import io.github.willerlucas.estacionamento.model.Vaga;
import io.github.willerlucas.estacionamento.model.VagaStatus;
import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.repository.TicketRepository;
import io.github.willerlucas.estacionamento.repository.VagaRepository;
import io.github.willerlucas.estacionamento.service.TicketService;
import io.github.willerlucas.estacionamento.service.VeiculoService;

@Service
public class TicketServiceImpl implements TicketService {

	/*
	 * metodos e tratamentos que ainda faltam impedir que um ticket finalizado seja
	 * finalizado novamente impedir que uma vaga ocupada seja ocupada dnv (falta
	 * transformar em um metodo) impedir que um veiculo com ticket em aberto seja
	 * estacionado
	 * 
	 */

	private static final long PRECO_HORA = 3;

	private Veiculo veiculo;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy-hh:mm")
	private LocalDateTime entrada = LocalDateTime.now();

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy-hh:mm")
	private LocalDateTime saida;

	private Vaga vaga;

	@Enumerated(EnumType.STRING)
	private TicketStatus status;

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

	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	VagaRepository vagaRepository;
	@Autowired
	VagaServiceImpl vagaService;
	@Autowired
	VeiculoService veiculoService;

	@Override
	public List<Ticket> findAll() {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket findById(long id) {

		return ticketRepository.findById(id).get();
	}

	@Override
	public Ticket save(Ticket ticket) throws VeiculoJaEstacionadoException {

		
		//verifica vaga e se veiculo ja está estacionado
		try {
		if(vagaService.verificaVaga(ticket.getVaga().getId())) throw new vagaJaOcupadaException(ticket.getVaga());
		if(verificaVeiculo(ticket)) throw new VeiculoJaEstacionadoException(ticket);
		} catch (vagaJaOcupadaException | VeiculoJaEstacionadoException e) {
			return null;
		}
		
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

		// verificando se o ticket já está finalizado
		try {
			if (ticket.getSaida() != null) throw new TicketJaFinalizadoException(ticket);
		} catch (TicketJaFinalizadoException e){
			return null;
		}

		ticket.setSaida(LocalDateTime.now());
		ticket.setStatus(TicketStatus.FINALIZADO);
		ticket.getVaga().setStatus(VagaStatus.LIVRE);

		long valorTotal = calcularPreco(ticket.getSaida(), ticket.getEntrada());
		System.out.println("chegou aqui    " + valorTotal);
		ticket.setPreco((int) valorTotal);

		ticketRepository.save(ticket);

		return ticket;

	}

	private long calcularPreco(LocalDateTime saida, LocalDateTime entrada) {

		// como o metodo trunca o valor recebido, somei +1 para que o ticket sempre
		// cobre A hora incial + as adicionais

		// ex: se ficar 1h30, vai pagar 2h (1 truncado + 1 adicional)
		return (((Duration.between(entrada, saida).toHours()) + 1) * PRECO_HORA);

	}

	@Override
	public boolean verificaVeiculo(Ticket ticket) {

		// percorre todos os ticket abertos verificando se tem algum ID correspondente
		// ai id do carro

		// Long veiculo = ticket.getVeiculo().getId();
		System.out.println("entrei na verificavao de vericulo");
		int verificaEstacionado = ticketRepository.verificaVeiculo(ticket.getVeiculo().getId());
		if (verificaEstacionado >= 1) {
			System.out.println("entrei na verificavao de vericulo ocupado");
			return true;
		}

		return false;
	}
}

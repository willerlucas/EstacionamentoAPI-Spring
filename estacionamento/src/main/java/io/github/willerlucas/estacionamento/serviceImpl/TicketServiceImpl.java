package io.github.willerlucas.estacionamento.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.willerlucas.estacionamento.config.validacao.TicketJaFinalizadoException;
import io.github.willerlucas.estacionamento.config.validacao.VeiculoJaEstacionadoException;
import io.github.willerlucas.estacionamento.config.validacao.vagaJaOcupadaException;
import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.model.TicketStatus;
import io.github.willerlucas.estacionamento.model.VagaStatus;
import io.github.willerlucas.estacionamento.repository.TicketRepository;
import io.github.willerlucas.estacionamento.repository.VagaRepository;
import io.github.willerlucas.estacionamento.service.TicketService;
import io.github.willerlucas.estacionamento.service.VeiculoService;

@Service
public class TicketServiceImpl implements TicketService {

	private static final long PRECO_HORA = 3;

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

		// verifica vaga e se veiculo ja está estacionado
		try {
			if (vagaService.verificaVaga(ticket.getVaga().getId()))
				throw new vagaJaOcupadaException(ticket.getVaga());
			if (verificaVeiculo(ticket))
				throw new VeiculoJaEstacionadoException("Veiculo ja está estacionado");
		} catch (vagaJaOcupadaException | VeiculoJaEstacionadoException e) {
			return null;
		}

		return ticketRepository.save(ticket);

	}

	// metodo para listar todos os tickets em aberto
	@Override
	public List<Ticket> listaAbertos() {

		List<Ticket> tickets = ticketRepository.findAll();

		List<Ticket> ticketsAbertos = tickets.stream().filter(t -> t.getStatus() == TicketStatus.ABERTO)
				.collect(Collectors.toList());

		return ticketsAbertos;

	}

	// metodo para listar todos os tickets finalizados
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
			if (ticket.getSaida() != null)
				throw new TicketJaFinalizadoException(ticket);
		} catch (TicketJaFinalizadoException e) {
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
		// a id do carro

		// tentei passar o objeto mas estava por algum motivo obscuro dando nullPointer
		int verificaEstacionado = ticketRepository.verificaVeiculo(ticket.getVeiculo().getId());

		if (verificaEstacionado >= 1)
			return true;

		return false;
	}
}

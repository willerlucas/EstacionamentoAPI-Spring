package io.github.willerlucas.estacionamento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.willerlucas.estacionamento.config.validacao.VeiculoJaEstacionadoException;
import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.repository.TicketRepository;
import io.github.willerlucas.estacionamento.repository.VagaRepository;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;
import io.github.willerlucas.estacionamento.service.ClienteService;
import io.github.willerlucas.estacionamento.service.TicketService;
import io.github.willerlucas.estacionamento.serviceImpl.TicketServiceImpl;
import io.github.willerlucas.estacionamento.serviceImpl.VagaServiceImpl;

@RestController
public class TicketController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	VeiculoRepository veiculoRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	TicketService ticketService;

	@Autowired
	VagaServiceImpl vagaService;

	@Autowired
	VagaRepository vagaRepository;

	// motodos

	// listando todos os ticket (finalizados e abertos)
	@GetMapping("/ticket")
	public List<Ticket> getTickets() {
		List<Ticket> ticket = ticketService.findAll();
		return ticket;
	}

	// listando tickets abertos
	@GetMapping("/ticket/aberto")
	public List<Ticket> getTicketsAbertos() {
		return ticketService.listaAbertos();
	}

	// listando tickets finalizados
	@GetMapping("/ticket/finalizado")
	public List<Ticket> getTicketsFinalizados() {
		return ticketService.listaFechados();
	}

	// recuperando um unico ticket
	@GetMapping("ticket/{id}")
	public Ticket getUniqueTicket(@PathVariable("id") long id) {
		Ticket ticket = ticketService.findById(id);
		return ticket;
	}

	// novoTicket(recebe um carro e uma vaga, ocupa a vaga)
	@PostMapping("ticket/adicionar")
	public ResponseEntity<Ticket> saveTicket(@RequestBody @Valid Ticket ticketParam, UriComponentsBuilder uriBuilder)
			throws VeiculoJaEstacionadoException {

		// o objetivo dessa linha foi instanciar o contrutor pra poder recuperar os
		// dados definidos na classe Modelo
		Ticket ticket = new Ticket(ticketParam);

		if (ticketService.save(ticket) == null) {
			System.out.println("entrou no nulo");
			return ResponseEntity.badRequest().build();
		} else {

			ticketService.save(ticket);
			// System.out.println(ticket);
			vagaService.ocuparVaga(ticket.getVaga().getId());

			URI uri = uriBuilder.path("/ticket/{id}").buildAndExpand(ticket.getId()).toUri();
			return ResponseEntity.created(uri).build(); // o que estava sendo apresentado era só o body mas o objeto nao
														// estava sendo criado
		}

	}

	// finalizarTicket(chama o metodo calcular preco e finaliza, libera a vaga)
	@PutMapping("ticket/finalizar/{id}")
	public ResponseEntity<Ticket> finalizarTicket(@PathVariable Long id, TicketServiceImpl ticketService) {
		Optional<Ticket> optionalTicket = ticketRepository.findById(id);

		
		if (optionalTicket.isPresent()) {

			//se estiver presente e receber valor null do metodo finalizar, responde bad
			//request, senão fianaliza
			if (ticketService.finalizar(id, ticketRepository) == null) {
				System.out.println("Esse ticket provavelmente ja foi finalizado");
				return ResponseEntity.badRequest().build();
				
			} else {

				ticketService.finalizar(id, ticketRepository);
				return ResponseEntity.ok().build();

			}
		}

		//se nao estiver presente retorna not found
		return ResponseEntity.notFound().build();
	}

}

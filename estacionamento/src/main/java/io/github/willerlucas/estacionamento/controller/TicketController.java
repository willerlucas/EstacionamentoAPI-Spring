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

import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.repository.TicketRepository;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;
import io.github.willerlucas.estacionamento.service.ClienteService;
import io.github.willerlucas.estacionamento.service.TicketService;
import io.github.willerlucas.estacionamento.serviceImpl.TicketServiceImpl;

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
	public ResponseEntity<Ticket> saveTicket(@RequestBody @Valid Ticket ticketParam, UriComponentsBuilder uriBuilder){
			
		//o objetivo dessa linha foi instanciar o contrutor pra poder recuperar os dados definidos na classe Modelo
		Ticket ticket = new Ticket(ticketParam);
		
		ticketService.save(ticket);
	
		//metodo para ocupar vaga
		
		
		URI uri = uriBuilder.path("/ticket/{id}").buildAndExpand(ticket.getId()).toUri();
		return ResponseEntity.created(uri).body(new Ticket(ticket)); //o que estava sendo apresentado era só o body mas o objeto nao estava sendo criado 
	}
	

	// finalizarTicket(chama o metodo calcular preco e finaliza, libera a vaga)
	@PutMapping("ticket/finalizar/{id}")
	public ResponseEntity<Ticket> finalizarTicket(@PathVariable Long id, TicketServiceImpl ticketService) {
		Optional<Ticket> optionalTicket = ticketRepository.findById(id);
		
		if (optionalTicket.isPresent()) {
			
			
			Ticket ticket = ticketService.finalizar(id, ticketRepository);
						
			System.out.println("chegou aqui");
			return ResponseEntity.ok(ticket);

		}
		
		return ResponseEntity.notFound().build();
	}
	
}

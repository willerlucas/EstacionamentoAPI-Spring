package io.github.willerlucas.estacionamento.service;

import java.util.List;

import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.repository.TicketRepository;

public interface TicketService {

	//metodos
	//método que retorna uma todos os tickets
		List<Ticket> findAll();
		
		//retorna um unico ticket
		Ticket findById(long id);
		
		//receber um ticket pra salvar no banco de dados
		Ticket save(Ticket ticket);
		
		
		//encontrar tickets abertos
		List<Ticket> listaAbertos();
		
		//encontrar tickets fechados
		List<Ticket> listaFechados();

		//fechando um ticket
		public Ticket finalizar(Long id, TicketRepository ticketRepository);

		//public Ticket finalizar(Ticket ticket);
		
		
		
	
	
}

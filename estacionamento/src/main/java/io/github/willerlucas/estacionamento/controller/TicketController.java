package io.github.willerlucas.estacionamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.github.willerlucas.estacionamento.model.Cliente;
import io.github.willerlucas.estacionamento.repository.ClienteRepository;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;
import io.github.willerlucas.estacionamento.service.ClienteService;

@RestController
public class TicketController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	VeiculoRepository veiculoRepository;
	
	//motodos
	
	
	
	//listando todos os ticket (finalizados e abertos)
	@GetMapping("ticket/{id}") 
	public Cliente getUniqueCliente(@PathVariable("id") long id) {
		
		//filtrando por Id
		Cliente cliente = clienteService.findById(id); 
		
		return cliente;
		
	}
	
	
	//listando tickets abertos

	//listando tickets finalizados
	
	//recuperando um unico ticket
	
	//novoTicket(recebe um carro e uma vaga, ocupa a vaga)
	
	//calcular preço (calcula o preço mas nao finaliza)
	
	//finalizarTicket(chama o metodo calcular preco e finaliza, libera a vaga)
		
	
}

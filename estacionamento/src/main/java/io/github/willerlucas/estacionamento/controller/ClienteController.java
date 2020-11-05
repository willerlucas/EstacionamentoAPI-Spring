package io.github.willerlucas.estacionamento.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;


import io.github.willerlucas.estacionamento.model.Cliente;
import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.repository.ClienteRepository;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;
import io.github.willerlucas.estacionamento.service.ClienteService;
import io.github.willerlucas.estacionamento.serviceImpl.ClienteServiceImpl;

@RestController
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	VeiculoRepository veiculoRepository;

	//recuperando todos os clientes
	@GetMapping("/cliente")
	public List<Cliente> getClientes() {
		List<Cliente> cliente = clienteService.findAll(); 
		return cliente;
	}
	
	//recuperando um unico cliente
	@GetMapping("cliente/{id}") 
	public Cliente getUniqueCliente(@PathVariable("id") long id) {
		
		//filtrando por Id
		Cliente cliente = clienteService.findById(id); 
		
		return cliente;
		
	}
		
	//cadastrando um cliente
	@PostMapping("cliente/adicionar") 
	@Transactional
	public ResponseEntity<Cliente> saveCliente(@RequestBody @Valid Cliente cliente, BindingResult result, RedirectAttributes attributes, UriComponentsBuilder uriBuilder) {
		
		clienteService.save(cliente);
		URI uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new Cliente(cliente));
	}
		
	//deletando um cliente
	@DeleteMapping("/cliente/remover/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id, ClienteServiceImpl clienteService) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			
			//Long idAuxiliar = (Long) id;
			clienteService.deleteClientebyId(id, clienteRepository);
			//clienteRepository.deleteById(id);
			return ResponseEntity.ok().build();
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//alterando cliente
	@PutMapping("cliente/alterar/{id}")
	@Transactional
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteServiceImpl clienteService) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		if (optional.isPresent()) {
			Cliente cliente = clienteService.atualizar(id, clienteRepository);
			return ResponseEntity.ok(new Cliente(cliente));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	//adicionando carros ao cliente
	@PutMapping("cliente/addVeiculo/cliente={idc}/veiculo={idv}")
	@Transactional
	public ResponseEntity<Cliente> addVeiculo(@PathVariable Long idc, @PathVariable Long idv, ClienteServiceImpl clienteService) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(idc);
		Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(idv);
		if (optionalCliente.isPresent() && optionalVeiculo.isPresent()) {
			clienteService.addVeiculo(idc, idv, clienteRepository, veiculoRepository);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//removendo carros do cliente
	@PutMapping("cliente/rmvVeiculo/cliente={idc}/veiculo={idv}")
	@Transactional
	public ResponseEntity<Cliente> rmvVeiculo(@PathVariable Long idc, @PathVariable Long idv, ClienteServiceImpl clienteService) {
		Optional<Cliente> optionalCliente = clienteRepository.findById(idc);
		Optional<Veiculo> optionalVeiculo = veiculoRepository.findById(idv);
		if (optionalCliente.isPresent() && optionalVeiculo.isPresent()) {
			clienteService.rmvVeiculo(idc, idv, clienteRepository, veiculoRepository);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	
}

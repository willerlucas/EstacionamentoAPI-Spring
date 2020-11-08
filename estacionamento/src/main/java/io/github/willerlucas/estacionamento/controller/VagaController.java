package io.github.willerlucas.estacionamento.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.willerlucas.estacionamento.model.Vaga;
import io.github.willerlucas.estacionamento.repository.VagaRepository;
import io.github.willerlucas.estacionamento.serviceImpl.VagaServiceImpl;

@RestController
public class VagaController {

	@Autowired
	VagaRepository vagaRepository;
	@Autowired
	VagaServiceImpl vagaService;
	
	//ocupar vaga
	@PutMapping("vaga/ocupar/{id}")
	@Transactional
	public ResponseEntity<Vaga> ocuparVaga(@PathVariable Long id, VagaServiceImpl vagaService) {
		Optional<Vaga> optional = vagaRepository.findById(id);
		if (optional.isPresent()) {
			//Vaga vaga = vagaService.ocuparVaga(id);
			vagaService.ocuparVaga(id);
			return ResponseEntity.ok(optional.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	//liberar vaga
	@PutMapping("vaga/liberar/{id}")
	@Transactional
	public ResponseEntity<Vaga> liberarVaga(@PathVariable Long id, VagaServiceImpl vagaService) {
		Optional<Vaga> optional = vagaRepository.findById(id);
		if (optional.isPresent()) {
			Vaga vaga = vagaService.liberarVaga(id, vagaRepository);
			return ResponseEntity.ok(new Vaga(vaga));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	
	//listar todas as vagas
	@GetMapping("/vaga")
	public List<Vaga> getVagas() {
		List<Vaga> vagas = vagaService.findAll(); 
		return vagas;
	}
	
	//recuperando um unico cliente
	@GetMapping("vaga/{id}") 
	public Vaga getUniqueVaga(@PathVariable("id") long id) {
		
		//filtrando por Id
		Vaga vaga = vagaService.findById(id); 
		
		return vaga;
		
	}
	
}

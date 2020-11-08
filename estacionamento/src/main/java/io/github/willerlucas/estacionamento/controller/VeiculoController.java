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

import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;
import io.github.willerlucas.estacionamento.service.VeiculoService;
import io.github.willerlucas.estacionamento.serviceImpl.VeiculoServiceImpl;

@RestController
public class VeiculoController {

	@Autowired
	VeiculoService veiculoService;
	
	@Autowired
	VeiculoRepository veiculoRepository;
	
	//recuperando todos os veiculos
		@GetMapping("/veiculo")
		public List<Veiculo> getVeiculos() {
//			List<Veiculo> veiculos = veiculoService.findAll(); 
//			return veiculos;
			return veiculoService.findAll();
		}
		
		//recuperando um unico veiculo
		@GetMapping("veiculo/{id}") 
		public Veiculo getUniqueVeiculo(@PathVariable("id") long id) {
			
			//filtrando por Id
//			Veiculo veiculo = veiculoService.findById(id); 
//			
//			
//			return veiculo;
			return veiculoService.findById(id);
			
		}
			
		//cadastrando um veiculo
		@PostMapping("veiculo/adicionar") 
		@Transactional
		public ResponseEntity<Veiculo> saveVeiculo(@RequestBody @Valid Veiculo veiculo, BindingResult result, RedirectAttributes attributes, UriComponentsBuilder uriBuilder) {
			
			if(veiculoService.save(veiculo) == null) {
				return ResponseEntity.badRequest().build();
			}
			
			URI uri = uriBuilder.path("/veiculo/{id}").buildAndExpand(veiculo.getId()).toUri();
			return ResponseEntity.created(uri).build();	
			
		}
			
		//deletando um veiculo
		@DeleteMapping("/veiculo/remover/{id}")
		@Transactional
		public ResponseEntity<?> remover(@PathVariable Long id, VeiculoServiceImpl veiculoService) {
			Optional<Veiculo> optional = veiculoRepository.findById(id);
			if (optional.isPresent()) {
				
				veiculoService.deleteVeiculobyId(id, veiculoRepository);
				return ResponseEntity.ok().build();
				
			}
			
			return ResponseEntity.notFound().build();
		}
		
		//alterando um veiculo
		@PutMapping("veiculo/alterar/{id}")
		@Transactional
		public ResponseEntity<Veiculo> atualizar(@PathVariable Long id, @RequestBody @Valid VeiculoServiceImpl veiculoService) {
			Optional<Veiculo> optional = veiculoRepository.findById(id);
			if (optional.isPresent()) {
				veiculoService.atualizar(id, veiculoRepository);
				return ResponseEntity.ok().build();
			}
			
			return ResponseEntity.notFound().build();
		}
	
	
}

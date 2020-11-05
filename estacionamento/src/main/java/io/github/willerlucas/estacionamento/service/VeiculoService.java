package io.github.willerlucas.estacionamento.service;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.model.VeiculoTipo;
import io.github.willerlucas.estacionamento.repository.ClienteRepository;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;


public interface VeiculoService {

	
	//atualizando veiculo
	public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository);
	
	//método que retorna uma todos os cliente
	List<Veiculo> findAll();
	
	//retorna um unico cliente
	Veiculo findById(long id);
	
	//receber um cliente pra salvar no banco de dados
	Veiculo save(Veiculo veiculo);
	
	public void deleteVeiculobyId(Long id, VeiculoRepository veiculoRepository);
	
	
}

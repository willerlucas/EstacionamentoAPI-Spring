package io.github.willerlucas.estacionamento.service;

import java.util.List;

import io.github.willerlucas.estacionamento.model.Veiculo;
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
	
	public boolean verificaVeiculoDono(Veiculo veiculo);

	
	
}

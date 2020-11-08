package io.github.willerlucas.estacionamento.service;

import java.util.List;

import io.github.willerlucas.estacionamento.model.Cliente;
import io.github.willerlucas.estacionamento.repository.ClienteRepository;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;

public interface ClienteService {
	
	//método que retorna uma todos os cliente
	List<Cliente> findAll();
	
	//retorna um unico cliente
	Cliente findById(long id);
	
	//receber um cliente pra salvar no banco de dados
	Cliente save(Cliente cliente);
	
	//atualizando o cliente
	public Cliente atualizar(Long id, ClienteRepository clienteRepository);

	
	//deletando cliente pelo id
	public void deleteClientebyId(Long id, ClienteRepository clienteRepository);
	
	//amarrando veiculo a um cliente
	public void addVeiculo(Long idc, Long idv, ClienteRepository clienteRepository,
			VeiculoRepository veiculoRepository);

	//removendo veiculo do cliente
	public void rmvVeiculo(Long idc, Long idv, ClienteRepository clienteRepository,
			VeiculoRepository veiculoRepository);
		
}

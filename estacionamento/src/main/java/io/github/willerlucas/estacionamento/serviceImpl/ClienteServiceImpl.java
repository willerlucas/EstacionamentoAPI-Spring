package io.github.willerlucas.estacionamento.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.willerlucas.estacionamento.model.Cliente;
import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.repository.ClienteRepository;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;
import io.github.willerlucas.estacionamento.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String cpf;

	@NotNull
	@NotEmpty
	private String telefone;

	private String email;
	
	@Autowired
	ClienteRepository clienteRepository;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public List<Cliente> findAll() {
		
		return clienteRepository.findAll();
	}

	@Override
	public Cliente findById(long id) {
		return clienteRepository.findById(id).get();
	}

	@Override
	public Cliente save(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	@Override
	public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
		Cliente cliente = clienteRepository.getOne(id);

		cliente.setCPF(this.cpf);
		cliente.setEmail(this.email);
		cliente.setNome(this.nome);
		cliente.setTelefone(this.telefone);

		return cliente;
	}

	@Override
	public void addVeiculo(Long idc, Long idv, ClienteRepository clienteRepository,
			VeiculoRepository veiculoRepository) {
		
		Cliente cliente = clienteRepository.getOne(idc);
		Veiculo veiculo = veiculoRepository.getOne(idv);

		cliente.addVeiculo(veiculo);
	}

	@Override
	public void rmvVeiculo(Long idc, Long idv, ClienteRepository clienteRepository,
			VeiculoRepository veiculoRepository) {
		
		Cliente cliente = clienteRepository.getOne(idc);
		Veiculo veiculo = veiculoRepository.getOne(idv);

		cliente.rmvVeiculo(veiculo);

	}

	@Override
	public void deleteClientebyId(Long id, ClienteRepository clienteRepository) {
		clienteRepository.deleteById(id);	
	}


}

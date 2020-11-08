package io.github.willerlucas.estacionamento.serviceImpl;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.model.VeiculoTipo;
import io.github.willerlucas.estacionamento.repository.ClienteRepository;
import io.github.willerlucas.estacionamento.repository.VeiculoRepository;
import io.github.willerlucas.estacionamento.service.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	private String placa;

	@NotNull
	private String modelo;

	@Enumerated(EnumType.STRING)
	private VeiculoTipo tipo;

	@NotNull
	private boolean zeroKm;

	@Autowired
	VeiculoRepository veiculoRepository;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public VeiculoTipo getTipo() {
		return tipo;
	}

	public void setTipo(VeiculoTipo tipo) {
		this.tipo = tipo;
	}

	public boolean isZeroKm() {
		return zeroKm;
	}

	public void setZeroKm(boolean zeroKm) {
		this.zeroKm = zeroKm;
	}

	// atualizando veiculo
	@Override
	public Veiculo atualizar(Long id, VeiculoRepository veiculoRepository) {
		Veiculo veiculo = veiculoRepository.getOne(id);

		veiculo.setModelo(this.modelo);
		veiculo.setTipo(this.tipo);

		if (veiculo.isZeroKm()) {
			veiculo.setPlaca("AAA-000");
		} else {
			veiculo.setPlaca(this.placa);
		}

		return veiculo;
	}

	@Override
	public List<Veiculo> findAll() {

		return veiculoRepository.findAll();
	}

	@Override
	public Veiculo findById(long id) {
		return veiculoRepository.findById(id).get();
	}

	@Override
	public Veiculo save(Veiculo veiculo) {
		try {
		return veiculoRepository.save(veiculo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void deleteVeiculobyId(Long id, VeiculoRepository veiculoRepository) {
		veiculoRepository.deleteById(id);
	}
	
	@Override
	public boolean verificaVeiculoDono(Veiculo veiculo) {

		int dono = veiculoRepository.verificaVeiculoDono(veiculo);
		if (dono >= 1)
			return true;
		return false;

	}

}

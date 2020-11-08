package io.github.willerlucas.estacionamento.serviceImpl;

import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.willerlucas.estacionamento.model.Vaga;
import io.github.willerlucas.estacionamento.model.VagaStatus;
import io.github.willerlucas.estacionamento.repository.VagaRepository;

@Service
public class VagaServiceImpl {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private VagaStatus status;

	@Autowired
	VagaRepository vagaRepository;
	
	
	
	public boolean verificaVaga(Long id) {

		System.out.println("Vaga id: " + id);
		Vaga vaga = vagaRepository.findById(id).get();
		
		if(vaga.getStatus() == VagaStatus.OCUPADA) {
			System.out.println("Vaga ocupada");
			return true;
			//return VagaOcupadaException();
		}
		return false;
	}


	
	public Vaga ocuparVaga(Long id) {
		
		Vaga vaga = vagaRepository.getOne(id);
		vaga.setStatus(VagaStatus.OCUPADA);
		vagaRepository.save(vaga);
		return vaga;
	}


	
	public Vaga liberarVaga(Long id2, VagaRepository vagaRepository2) {
		Vaga vaga = vagaRepository2.getOne(id2);
		vaga.setStatus(VagaStatus.LIVRE);
		
		return vaga;
	}


	
	public List<Vaga> findAll() {
		// TODO Auto-generated method stub
		return vagaRepository.findAll();
	}


	
	public Vaga findById(long id) {
		return vagaRepository.findById(id).get();
	}



}

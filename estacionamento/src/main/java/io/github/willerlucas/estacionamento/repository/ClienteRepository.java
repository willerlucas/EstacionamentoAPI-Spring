package io.github.willerlucas.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.willerlucas.estacionamento.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	//vazio mesmo pq todos os metodos estão sendo herdados
}

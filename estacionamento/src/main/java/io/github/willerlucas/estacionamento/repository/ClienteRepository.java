package io.github.willerlucas.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.willerlucas.estacionamento.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

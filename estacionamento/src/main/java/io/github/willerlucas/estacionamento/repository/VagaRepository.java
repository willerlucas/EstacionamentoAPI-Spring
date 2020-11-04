package io.github.willerlucas.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.willerlucas.estacionamento.model.Vaga;

public interface VagaRepository extends JpaRepository<Vaga, Long> {

}

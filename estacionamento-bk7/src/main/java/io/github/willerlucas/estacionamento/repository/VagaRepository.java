package io.github.willerlucas.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.willerlucas.estacionamento.model.Vaga;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {

}

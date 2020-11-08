package io.github.willerlucas.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.willerlucas.estacionamento.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	@Query("SELECT count(*) FROM Cliente c WHERE c.veiculos = ?1")
	int verificaVeiculoDono(Veiculo veiculo);

}

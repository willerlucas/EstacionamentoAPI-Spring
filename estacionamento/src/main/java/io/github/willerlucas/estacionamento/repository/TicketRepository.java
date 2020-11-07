package io.github.willerlucas.estacionamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.willerlucas.estacionamento.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query("SELECT count(*) FROM Ticket t WHERE t.status = 'ABERTO' AND t.veiculo.id = ?1")
	public int verificaVeiculo(Long id);

}

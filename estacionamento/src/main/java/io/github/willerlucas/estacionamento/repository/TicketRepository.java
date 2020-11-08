package io.github.willerlucas.estacionamento.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.willerlucas.estacionamento.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query("SELECT count(*) FROM Ticket t WHERE t.status = 'ABERTO' AND t.veiculo.id = ?1")
	public int verificaVeiculo(Long id);
	
//	@Query("SELECT Ticket.id FROM Ticket t WHERE t.status = 'ABERTO' AND t.veiculo.placa = ?1")
//	public Optional<Ticket> findByPlaca(String placa);
	
	@Query("SELECT t FROM Ticket t WHERE t.status = 'ABERTO' AND t.veiculo.placa = ?1")
	Ticket findByVeiculoPlaca(String placa);

}

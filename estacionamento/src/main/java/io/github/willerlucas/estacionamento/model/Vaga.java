package io.github.willerlucas.estacionamento.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_vaga")
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private VagaStatus status;
	
	public Vaga(){
		
	}
	
	public Vaga(Vaga vaga) {
		this.status = vaga.status;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VagaStatus getStatus() {
		return status;
	}

	public void setStatus(VagaStatus status) {
		this.status = status;
	}
	
	
	
}
/*
  public Ticket preparaTesteBasico(int qtAtivos, int qtAtivosPorVeiculo) {
        Ticket ticketParaTestar = getTicket();

 

        when(ticketRepositoryMock.getQuantidadeTicketsAtivos()).thenReturn(qtAtivos);
        when(ticketRepositoryMock.existsByVeiculo(ticketParaTestar.getVeiculo().getPlaca()))
                .thenReturn(qtAtivosPorVeiculo);
        when(clienteRepositoryMock.getByCpf(ticketParaTestar.getCliente().getCpf()))
                .thenReturn(ticketParaTestar.getCliente());
        when(clienteRepositoryMock.save(ticketParaTestar.getCliente())).thenReturn(ticketParaTestar.getCliente());
        when(veiculoRepositoryMock.getByPlaca(ticketParaTestar.getVeiculo().getPlaca()))
                .thenReturn(ticketParaTestar.getVeiculo());
        when(veiculoRepositoryMock.save(ticketParaTestar.getVeiculo())).thenReturn(ticketParaTestar.getVeiculo());
        when(ticketRepositoryMock.save(ticketParaTestar)).thenReturn(ticketParaTestar);

 

        return ticketParaTestar;
    }
 */





























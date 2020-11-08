package io.github.willerlucas.estacionamento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.willerlucas.estacionamento.config.validacao.VeiculoJaEstacionadoException;
import io.github.willerlucas.estacionamento.model.Cliente;
import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.model.Vaga;
import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.model.VeiculoTipo;
import io.github.willerlucas.estacionamento.repository.TicketRepository;
import io.github.willerlucas.estacionamento.repository.VagaRepository;
import io.github.willerlucas.estacionamento.service.ClienteService;
import io.github.willerlucas.estacionamento.service.TicketService;
import io.github.willerlucas.estacionamento.service.VeiculoService;
import io.github.willerlucas.estacionamento.serviceImpl.TicketServiceImpl;

@SpringBootTest
class TicketTestes {

	private static TicketServiceImpl ticketService;

	private static TicketRepository ticketRepositoryMock = mock(TicketRepository.class);

	private static VagaRepository vagaRepositoryMock = mock(VagaRepository.class);

	private static VeiculoService veiculoService;

	private static ClienteService clienteService;

	@BeforeAll
	public static void init() {

	}

	// testando um estacionamento concluido
	@Test
	public void testEstacionamentoBemSucedido() {

		
		
	}

	@Test
	public void testEstacionandoVeiculoJaEstacionado() {

	}
	
	@Test
	public void testEstacionandoVeiculoVagaJaOcupada() {

	}
	
	@Test
	public void testFinalizandoTicketBemSucedido() {

	}
	
	@Test
	public void testFinalizandoTicketJaFinalizado() {

	}	

	
	
	//preparando mocks
    public Ticket preparaMocks() {
		return null;
 
    	
    }

	public Veiculo veiculo1() {

		// criando veiculo 1

		Veiculo veiculo1 = new Veiculo();
		veiculo1.setId(1L);
		veiculo1.setPlaca("ABC-1234");
		veiculo1.setModelo("Ferrari");
		veiculo1.setTipo(VeiculoTipo.CARRO);
		veiculo1.setZeroKm(false);
		return veiculo1;
	}
	// criando veiculo 2

	public Veiculo veiculo2() {
		Veiculo veiculo2 = new Veiculo();
		veiculo2.setId(2L);
		veiculo2.setPlaca("DEF-4567");
		veiculo2.setModelo("Lambo");
		veiculo2.setTipo(VeiculoTipo.CARRO);
		veiculo2.setZeroKm(false);
		return veiculo2;
	}

	public Cliente cliente() {
		// criando cliente 1

		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Willer");
		cliente.setCPF("41315748732");
		cliente.setEmail("willer@email.com");
		cliente.setTelefone("(48)998877665");
		cliente.addVeiculo(veiculo1());
		return cliente;
	}

}

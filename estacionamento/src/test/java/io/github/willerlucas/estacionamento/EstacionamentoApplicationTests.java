package io.github.willerlucas.estacionamento;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.willerlucas.estacionamento.controller.TicketController;
import io.github.willerlucas.estacionamento.model.Cliente;
import io.github.willerlucas.estacionamento.model.Ticket;
import io.github.willerlucas.estacionamento.model.TicketStatus;
import io.github.willerlucas.estacionamento.model.Vaga;
import io.github.willerlucas.estacionamento.model.VagaStatus;
import io.github.willerlucas.estacionamento.model.Veiculo;
import io.github.willerlucas.estacionamento.model.VeiculoTipo;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(Lifecycle.PER_CLASS)
class EstacionamentoApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	@Autowired
    private TicketController meuController;
	
	@BeforeAll
	public void  setUp() {
	    mockMvc = MockMvcBuilders.standaloneSetup(meuController).build();
	}
	


    @Autowired
    private ObjectMapper objectMapper;

//	@Autowired
//	private TicketServiceImpl ticketService;
	
	@Test
	void EstacionamentoBemSucedido() throws JsonProcessingException, Exception{
		
		Veiculo veiculo1 = new Veiculo();
		veiculo1.setId(1L);
		veiculo1.setPlaca("ABC-1234");
		veiculo1.setModelo("Ferrari");
		veiculo1.setTipo(VeiculoTipo.CARRO);
		veiculo1.setZeroKm(false); 
		
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Willer");
		cliente.setCPF("41315748732");
		cliente.setEmail("willer@email.com");
		cliente.setTelefone("(48)998877665");
		cliente.addVeiculo(veiculo1);
		
		Vaga vaga = new Vaga();
		vaga.setId(1L);
		vaga.setStatus(VagaStatus.LIVRE);
		
		Ticket ticket = new Ticket();
		ticket.setVeiculo(veiculo1);
		ticket.setVaga(vaga);
		
		mockMvc.perform(post("/ticket/adicionar")
				.contentType("Application/json")
				.content(objectMapper.writeValueAsString(ticket)))
		.andExpect(status().isCreated());
		
		
		 Assertions.assertEquals(ticket.getVeiculo().getId(), 1L);
		 Assertions.assertEquals(ticket.getVaga().getId(), 1L);
		
		
	}
	
	@Test
	void EstacionandoVeiculoJaEstacionado() throws JsonProcessingException, Exception{
		
		Veiculo veiculo1 = new Veiculo();
		veiculo1.setId(1L);
		veiculo1.setPlaca("ABC-1234");
		veiculo1.setModelo("Ferrari");
		veiculo1.setTipo(VeiculoTipo.CARRO);
		veiculo1.setZeroKm(false); 
		
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Willer");
		cliente.setCPF("41315748732");
		cliente.setEmail("willer@email.com");
		cliente.setTelefone("(48)998877665");
		cliente.addVeiculo(veiculo1);
		
		Vaga vaga = new Vaga();
		vaga.setId(1L);
		vaga.setStatus(VagaStatus.LIVRE);
		
		Vaga vaga2 = new Vaga();
		vaga.setId(2L);
		vaga.setStatus(VagaStatus.LIVRE);
		
		Ticket ticket = new Ticket();
		ticket.setVeiculo(veiculo1);
		ticket.setVaga(vaga);
		
		Ticket ticket2 = new Ticket();
		ticket.setVeiculo(veiculo1);
		ticket.setVaga(vaga2);
		
		mockMvc.perform(post("/ticket/adicionar")
				.contentType("Application/json")
				.content(objectMapper.writeValueAsString(ticket2)))
		.andExpect(status().isBadRequest());
		
		
		 Assertions.assertEquals(ticket2.getVeiculo(), null);
		 Assertions.assertEquals(ticket2.getVaga(), null);
		
	}
	
	@Test
	void EstacionandoVeiculoEmVagaOcupada() throws JsonProcessingException, Exception{
		
		Veiculo veiculo1 = new Veiculo();
		veiculo1.setId(1L);
		veiculo1.setPlaca("ABC-1234");
		veiculo1.setModelo("Ferrari");
		veiculo1.setTipo(VeiculoTipo.CARRO);
		veiculo1.setZeroKm(false); 
		
		Veiculo veiculo2 = new Veiculo();
		veiculo1.setId(2L);
		veiculo1.setPlaca("BCA-1234");
		veiculo1.setModelo("LAMBO");
		veiculo1.setTipo(VeiculoTipo.CARRO);
		veiculo1.setZeroKm(false); 
		
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setNome("Willer");
		cliente.setCPF("41315748732");
		cliente.setEmail("willer@email.com");
		cliente.setTelefone("(48)998877665");
		cliente.addVeiculo(veiculo1);
		
		Vaga vaga1 = new Vaga();
		vaga1.setId(1L);
		vaga1.setStatus(VagaStatus.LIVRE);
		
		Vaga vaga2 = new Vaga();
		vaga2.setId(2L);
		vaga2.setStatus(VagaStatus.LIVRE);
		
		Ticket ticket = new Ticket();
		ticket.setVeiculo(veiculo1);
		ticket.setVaga(vaga1);
		
		Ticket ticket2 = new Ticket();
		ticket.setVeiculo(veiculo2);
		ticket.setVaga(vaga1);
		
		mockMvc.perform(post("/ticket/adicionar")
				.contentType("Application/json")
				.content(objectMapper.writeValueAsString(ticket2)))
		.andExpect(status().isBadRequest());
		
		
		 Assertions.assertEquals(ticket2.getVeiculo(), null);
		 Assertions.assertEquals(ticket2.getVaga(), null);
		
	}
	
	@Test
	void FinalizandoTicketComSucesso() throws JsonProcessingException, Exception{
		
		Veiculo veiculo1 = new Veiculo();
		veiculo1.setId(1L);
		veiculo1.setPlaca("ABC-1234");
		veiculo1.setModelo("Ferrari");
		veiculo1.setTipo(VeiculoTipo.CARRO);
		veiculo1.setZeroKm(false); 
		
		Vaga vaga1 = new Vaga();
		vaga1.setId(1L);
		vaga1.setStatus(VagaStatus.LIVRE);

		Ticket ticket = new Ticket();
		ticket.setVeiculo(veiculo1);
		ticket.setVaga(vaga1);
		
		
		mockMvc.perform(post("/ticket/adicionar")
				.contentType("Application/json")
				.content(objectMapper.writeValueAsString(ticket)));
		
		mockMvc.perform(put("/ticket/finalizar/{id}")
				.contentType("Application/json")
				.content(objectMapper.writeValueAsString(ticket)))
		.andExpect(status().isOk());
		
		
		 Assertions.assertEquals(ticket.getStatus(), TicketStatus.FINALIZADO);

		
	}
	
	@Test
	void FinalizandoTicketJaFinalizado() throws JsonProcessingException, Exception{
		
		Veiculo veiculo1 = new Veiculo();
		veiculo1.setId(1L);
		veiculo1.setPlaca("ABC-1234");
		veiculo1.setModelo("Ferrari");
		veiculo1.setTipo(VeiculoTipo.CARRO);
		veiculo1.setZeroKm(false); 
		
		Vaga vaga1 = new Vaga();
		vaga1.setId(1L);
		vaga1.setStatus(VagaStatus.LIVRE);

		Ticket ticket = new Ticket();
		ticket.setId(100L);
		ticket.setVeiculo(veiculo1);
		ticket.setVaga(vaga1);
	
		
		
		
		mockMvc.perform(post("/ticket/adicionar")
				.contentType("Application/json")
				.content(objectMapper.writeValueAsString(ticket)));
		
		String id = ticket.getId().toString();
		System.out.println(id);
		
		mockMvc.perform(put("/ticket/finalizar/"+ticket.getId())
				.contentType("Application/json")
				.content(objectMapper.writeValueAsString(ticket)));
		
		mockMvc.perform(put("/ticket/finalizar/"+ticket.getId())
				.contentType("Application/json")
				.content(objectMapper.writeValueAsString(ticket)))
		.andExpect(status().isBadRequest());
		
		
		// Assertions.assertEquals(ticket.getStatus(), TicketStatus.FINALIZADO);

		
	}

}

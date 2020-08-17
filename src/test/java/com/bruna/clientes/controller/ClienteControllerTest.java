package com.bruna.clientes.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bruna.clientes.domain.Cliente;
import com.bruna.clientes.domain.dto.ClienteDTO;
import com.bruna.clientes.repository.ClienteRepository;
import com.bruna.clientes.service.ClienteService;
import com.bruna.clientes.service.exceptions.ObjectNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClienteController.class)
@AutoConfigureMockMvc
public class ClienteControllerTest {
	
	static final String API = "/api/clientes";
	static final MediaType JSON = MediaType.APPLICATION_JSON;
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	ClienteService service;

	@Test
	public void deveCriarUmNovoCliente() throws Exception {
		//Cenário
		Cliente cli1 = new Cliente(null, "45775660013", "Walter White", "walter@gmail.com");
		ClienteDTO cliDto = new ClienteDTO(cli1);
		
		Mockito.when(service.insert(Mockito.any(Cliente.class))).thenReturn(cli1);
		String json = new ObjectMapper().writeValueAsString(cliDto);
		
		//Ação e Verificação
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(API)
				.accept(JSON)
				.contentType(JSON)
				.content(json);
		mvc
			.perform(request)
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("id").value(cli1.getId()))
			.andExpect(MockMvcResultMatchers.jsonPath("cpf").value(cli1.getCpf()))
			.andExpect(MockMvcResultMatchers.jsonPath("nome").value(cli1.getNome()))
			.andExpect(MockMvcResultMatchers.jsonPath("email").value(cli1.getEmail()));			
	}
	
	@Test
	public void deveLocalizarUmClientePorId() throws Exception {
		//Cenário
		Cliente cli1 = new Cliente(1L, "45775660013", "Walter White", "walter@gmail.com");
		
		Mockito.when(service.findById(1L)).thenReturn(cli1);

		
		//Ação e verificacao
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
													.get( API.concat("/1")  )
													.accept( JSON )
													.contentType( JSON );

		mvc
			.perform(request)
			.andExpect( MockMvcResultMatchers.status().isOk()  );
			
		;
	}
	
	@Test
	public void deveLancarErroQuandoClienteNaoExiste() throws Exception {
		//Cenário
		Mockito.when(service.findById(1L)).thenThrow(ObjectNotFoundException.class);
		
		//execucao e verificacao
				MockHttpServletRequestBuilder request = MockMvcRequestBuilders
															.get( API.concat("/1")  )
															.accept( JSON )
															.contentType( JSON );
				mvc
					.perform(request)
					.andExpect( MockMvcResultMatchers.status().isNotFound() );	
		
	}
	
	

}

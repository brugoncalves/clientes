package com.bruna.clientes.service;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bruna.clientes.domain.Cliente;
import com.bruna.clientes.repository.ClienteRepository;
import com.bruna.clientes.service.exceptions.ObjectNotFoundException;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClienteServiceTest {
	
	@Autowired
	ClienteService service;
	
	@Autowired
	ClienteRepository repository;
	
	
	@Test
	public void deveInserirUmCliente() {
		//Cenário
		repository.deleteAll();
		
		//Ação
		Cliente cliente = new Cliente(null, "45775660013", "Walter White", "walter@gmail.com");
		service.insert(cliente);
	}
	
	@Test
	public void naoDeveCadastrarUmClienteComEmailJaExistente() {
		//Cenário
		Cliente cli1 = new Cliente(null, "45775660013", "Walter White", "walter@gmail.com");
		repository.save(cli1);
		Cliente cli2 = new Cliente(null, "82339187095", "Jesse Pinkman", "walter@gmail.com");
		
		//Ação
		service.insert(cli2);
	}
	
	@Test
	public void naoDeveCadastrarUmClienteComCPFJaExistente() {
		//Cenário
		Cliente cli1 = new Cliente(null, "45775660013", "Walter White", "walter@gmail.com");
		repository.save(cli1);
		Cliente cli2 = new Cliente(null, "45775660013", "Jesse Pinkman", "jesse@gmail.com");
		
		//Ação
		service.insert(cli2);
		
	}
	
	@Test
	public void deveLocalizarUmClientePeloId() {
		//Cenário
		Cliente cliente = new Cliente(1L, "45775660013", "Walter White", "walter@gmail.com");
		repository.save(cliente);
		
		//Ação
		service.findById(cliente.getId());
	}
	
	@Test
	public void deveLocalizarTodosOsClientes() {
		//Cenário
		Cliente cli1 = new Cliente(null, "45775660013", "Walter White", "walter@gmail.com");
		Cliente cli2 = new Cliente(null, "82339187095", "Jesse Pinkman", "jesse@gmail.com");
		Cliente cli3 = new Cliente(null, "00647378027", "Gus Fring", "gustavo@gmail.com");
		repository.saveAll(Arrays.asList(cli1, cli2, cli3));
		
		//Ação
		service.findAll(1, 24, "nome", "ASC");
	}
	
	@Test
	public void deveLancarErroQuandoNaoLocalizarOClientePorId() {
		Assertions.assertThrows(ObjectNotFoundException.class, () -> {
			//Cenário
			repository.deleteAll();
			
			//Ação
			service.findById(1L);
			
		});	
	}
	
	@Test
	public void deveExcluirUmCliente() {
		//Cenário
		Cliente cli1 = new Cliente(null, "45775660013", "Walter White", "walter@gmail.com");
		repository.save(cli1);
		
		//Ação
		service.delete(cli1.getId());
	}
	
	@Test
	public void naoDeveExcluirUmClienteComIdQueNaoExiste() {
		Assertions.assertThrows(ObjectNotFoundException.class, () -> {
			//Cenário
			repository.deleteAll();
			
			//Ação
			service.delete(1L);
		});
	}
}

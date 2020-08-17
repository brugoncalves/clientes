package com.bruna.clientes.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bruna.clientes.domain.Cliente;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClienteRepositoryTest {

	@Autowired
	ClienteRepository repository;
	
	@Test
	public void deveVerificarAExistênciaDeUmEmail() {
		
		//Cenário
		Cliente cliente = new Cliente(null, "38838937818", "Bruna Gonçalves", "bruna@gmail.com");
		repository.save(cliente);
		
		//Execução
		Cliente resultado = repository.findByEmail("bruna@gmail.com");
		
		//Verificação
		Assertions.assertThat(resultado).isNotNull();
	}
	
	@Test
	public void deveVerificarQueOEmailNãoExiste() {
		
		//Cenário
		repository.deleteAll();
		
		//Execução
		Cliente resultado = repository.findByEmail("bruna@gmail.com");
		
		//Verificação
		Assertions.assertThat(resultado).isNull();
	}
	
	@Test
	public void deveVerificarQueOCPFExiste() {
		
		//Cenário
		Cliente cliente = new Cliente(null, "38838937818", "Bruna Gonçalves", "bruna@gmail.com");
		repository.save(cliente);
		
		//Execução
		Cliente resultado = repository.findByCpf("38838937818");
		
		//Verificação
		Assertions.assertThat(resultado).isNotNull();	
	}
	
	@Test
	public void deveVerificarQueOCPFNaoExiste() {
		
		//Cenário
		repository.deleteAll();
		
		//Execução
		Cliente resultado = repository.findByCpf("38838937818");
		
		//Verificação
		Assertions.assertThat(resultado).isNull();
	}
}

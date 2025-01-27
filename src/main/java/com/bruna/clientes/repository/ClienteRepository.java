package com.bruna.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bruna.clientes.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByCpf(String cpf);
	
	Cliente findByEmail(String email);

}

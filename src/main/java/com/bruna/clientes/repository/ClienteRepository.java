package com.bruna.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruna.clientes.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}

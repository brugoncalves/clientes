package com.bruna.clientes.domain.dto;

import java.io.Serializable;

import com.bruna.clientes.domain.Cliente;

public class ClienteDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private String nome;
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		cpf = cliente.getCpf();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}

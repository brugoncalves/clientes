package com.bruna.clientes.domain.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.bruna.clientes.domain.Cliente;

public class ClienteDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message="O preenchimento do CPF é obrigatório")
	private String cpf;
	
	@NotEmpty(message="O preenchimento do nome é obrigatório")
	@Length(min=2, max=80, message="O nome deve conter entre 5 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message="O preenchimento do email é obrigatório")
	@Email(message="Email inválido")
	@Column(unique=true)
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

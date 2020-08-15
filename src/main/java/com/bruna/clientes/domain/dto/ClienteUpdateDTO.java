package com.bruna.clientes.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.bruna.clientes.domain.Cliente;
import com.bruna.clientes.service.validations.ClienteUpdate;

@ClienteUpdate
public class ClienteUpdateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotEmpty(message="O nome deve ser preenchido")
	@Length(min=2, max=80, message="O nome deve conter entre 2 e 80 letras")
	private String nome;
	
	@NotEmpty(message="O email deve ser preenchido")
	@Email(message="O e-mail informado não é válido")
	private String email;
	
	public ClienteUpdateDTO() {
		
	}
	
	public ClienteUpdateDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

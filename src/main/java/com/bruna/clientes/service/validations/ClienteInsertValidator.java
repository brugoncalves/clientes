package com.bruna.clientes.service.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruna.clientes.controller.exceptions.FieldMessage;
import com.bruna.clientes.domain.Cliente;
import com.bruna.clientes.domain.dto.ClienteDTO;
import com.bruna.clientes.repository.ClienteRepository;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTO>{

	
	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente cpf = repository.findByCpf(dto.getCpf());
		
		//Verifica se o CPF já existe na base de dados
		if(cpf != null) {
			list.add(new FieldMessage("cpf", "CPF já cadastrado"));
		}
		
		Cliente email = repository.findByEmail(dto.getEmail());
		
		//Verifica se o Email já existe na base de dados
		if(email != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
		
	}
}

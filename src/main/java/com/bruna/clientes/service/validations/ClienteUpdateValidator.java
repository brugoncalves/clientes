package com.bruna.clientes.service.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.bruna.clientes.controller.exceptions.FieldMessage;
import com.bruna.clientes.domain.Cliente;
import com.bruna.clientes.domain.dto.ClienteUpdateDTO;
import com.bruna.clientes.repository.ClienteRepository;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteUpdateDTO> {
	
	
	@Override
	public void initialize(ClienteUpdate ann) {
		
	}
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private HttpServletRequest request;

	@Override
	public boolean isValid(ClienteUpdateDTO objDto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId = Long.parseLong(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		Cliente email = clienteRepository.findByEmail(objDto.getEmail());
		
		if(email != null && !email.getId().equals(uriId)) {
			list.add(new FieldMessage("email","Email já cadastrado."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
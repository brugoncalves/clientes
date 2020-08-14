package com.bruna.clientes.service.exceptions;

public class ObjectNotFoundException extends RuntimeException{


	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(Object id) {
		super("Cliente não encontrado. Id: " + id);
	}

}

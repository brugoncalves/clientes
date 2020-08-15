package com.bruna.clientes.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruna.clientes.domain.Cliente;
import com.bruna.clientes.domain.dto.ClienteDTO;
import com.bruna.clientes.domain.dto.ClienteUpdateDTO;
import com.bruna.clientes.repository.ClienteRepository;
import com.bruna.clientes.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow( () -> new ObjectNotFoundException(id));
	}
	
	public Page<Cliente> findAll(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repository.save(cliente);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(id);
		}
	}
	
	
	public Cliente update(Cliente obj) {
		try {
			Cliente newObj = findById(obj.getId());
			updateData(newObj, obj);
			return repository.save(newObj);
		} catch(EntityNotFoundException e) {
			throw new ObjectNotFoundException(obj.getId());
		}
		
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente fromDTO(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getCpf(), clienteDto.getNome(), clienteDto.getEmail());
	}
	
	public Cliente fromDTO(ClienteUpdateDTO clienteDto) {
		return new Cliente(null, null, clienteDto.getNome(), clienteDto.getEmail());
	}
}

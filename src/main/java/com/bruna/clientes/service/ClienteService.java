package com.bruna.clientes.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.bruna.clientes.domain.Cliente;
import com.bruna.clientes.domain.dto.ClienteDTO;
import com.bruna.clientes.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	
	public Cliente findById(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow();
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
		repository.deleteById(id);
	}
	
	
	public Cliente update(Cliente obj) {
		Cliente newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}
	
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setCpf(obj.getCpf());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente fromDTO(ClienteDTO clienteDto) {
		return new Cliente(clienteDto.getId(), clienteDto.getCpf(), clienteDto.getNome(), clienteDto.getEmail());
	}
}

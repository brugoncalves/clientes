package com.bruna.clientes.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bruna.clientes.domain.Cliente;
import com.bruna.clientes.domain.dto.ClienteDTO;
import com.bruna.clientes.domain.dto.ClienteUpdateDTO;
import com.bruna.clientes.service.ClienteService;

@RestController
@RequestMapping("api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
		Cliente cliente = service.findById(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@GetMapping
	public ResponseEntity<Page<Cliente>> findAll(
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy,
			@RequestParam(value="direction", defaultValue="ASC") String direction
			){
				Page<Cliente> clientsPage = service.findAll(page, linesPerPage, orderBy, direction);
				return ResponseEntity.ok().body(clientsPage);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO clienteDto){
		Cliente cliente = service.fromDTO(clienteDto);
		cliente = service.insert(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Cliente> update(@Valid @RequestBody ClienteUpdateDTO clienteDto, @PathVariable Long id){
		Cliente obj = service.fromDTO(clienteDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

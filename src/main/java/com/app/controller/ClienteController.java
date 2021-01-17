package com.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ClienteDTO;
import com.app.model.Cliente;
import com.app.model.ClienteBuilder;
import com.app.repository.ClienteRepository;
import com.app.validator.ClienteValidatorErrorBuilder;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(value = "*")
public class ClienteController {

	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	private ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@PostMapping(value="/cliente", produces = MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente, Errors errors){
		logger.info("{}", cliente);
		
		if(errors.hasErrors()) {
			return ResponseEntity.badRequest().body(ClienteValidatorErrorBuilder.fromBindingErrors(errors));
		}
		clienteRepository.save(cliente);
		return ResponseEntity.ok(new ClienteDTO(cliente));
	}
	
	
	@GetMapping(value="/cliente", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ClienteDTO>> getAllCliente(){
		logger.info("====[getAllCliente]====");
		List<Cliente> clienteList = this.clienteRepository.findAll();
		if(clienteList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(ClienteDTO.getClienteDTO(clienteList));
	}
	
	@GetMapping(value="/cliente/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDTO> getById(@PathVariable("id") long id){
		Cliente cliente = this.clienteRepository.findById(id);
		if(cliente == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(new ClienteDTO(cliente));
	}
	
	@GetMapping("/cliente/nombre/{nombre}")
	public ResponseEntity<List<ClienteDTO>> getByName(@PathVariable("nombre") String nombre){
		List<Cliente> clienteList = this.clienteRepository.findByName(nombre);
		if(clienteList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(ClienteDTO.getClienteDTO(clienteList));
	}
	
	@DeleteMapping(value="/cliente/{id}")
	public ResponseEntity<?> deleteCliente(@PathVariable("id") long id){
		logger.info("{}", id);
		this.clienteRepository.delete(ClienteBuilder.create().widthid(id).builder());
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}

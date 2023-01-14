package com.my.apirest.controllers;

import com.my.apirest.models.Address;
import com.my.apirest.models.Person;
import com.my.apirest.services.AddressService;
import com.my.apirest.services.PersonService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//TODO implementar try catch com retorno de erro customizado
//garantir que toda a lógica está na camada de serviços
//analisar boas práticas pra ver se não fica "estranho" o serviço de pessoa ter visibilidade do serviço de endereço
@RestController
@RequestMapping(value = "/person")
public class PersonController
{
	@Autowired
	private PersonService personService;

	@Autowired
	private AddressService addressService;

	@PostMapping
	private ResponseEntity<Person> create(@RequestBody Person person)
	{
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(personService.create(person).getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Person> findById(@PathVariable long id)
	{
		return ResponseEntity.ok().body(this.personService.findById(id));
	}

	@GetMapping
	public ResponseEntity<List<Person>> findAll()
	{
		return ResponseEntity.ok().body(personService.findAll());
	}

	@GetMapping(value = "/{id}/address")
	public ResponseEntity<List<Address>> findAll(@PathVariable long id)
	{
		return ResponseEntity.ok().body(personService.findAdresses(id));
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Person> update(@PathVariable long id, @RequestBody Person person)
	{
		return ResponseEntity.ok().body(personService.update(id, person));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id)
	{
		personService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}/add-new-address")
	public ResponseEntity<Address> newAddress(@PathVariable long id, @RequestBody Address address)
	{
		if (address.isMainAddress()){
			personService.updateActiveMainAddress(id, address);
		}

		return ResponseEntity.ok().body(addressService.create(address, personService.findById(id)));
	}

	@PutMapping(value = "/{id}/main-address/{addressId}")
	private ResponseEntity<Address> newMainAddress(@PathVariable long id, @PathVariable long addressId)
	{
		return ResponseEntity.ok().body(personService.setNewMainAddress(id, addressId));
	}
}

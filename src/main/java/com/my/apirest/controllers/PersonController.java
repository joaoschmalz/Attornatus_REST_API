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
		List<Address> addresses = person.getAddress();

		if (hasOnlyOneMainAddress(addresses))
		{
			Person newPerson = personService.create(new Person(person.getName(), person.getBirthdate()));
			for (Address address : addresses)
			{
				addressService.create(address, newPerson);
			}

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newPerson.getId()).toUri();

			return ResponseEntity.created(uri).build();
		}
		return null;
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
		if (hasOnlyOneMainAddress(person.getAddress()) && isValidPersonInfo(person))
		{
			Person newPerson = personService.update(id, person);

			return ResponseEntity.ok().body(newPerson);
		}
		return null;
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
		Person personToEdit = this.findById(id).getBody();

		if (personToEdit != null && isValidAddressInfo(address))
		{
			if (address.isMainAddress())
			{
				personService.updateActiveMainAddress(personToEdit.getAddress());
			}

			return ResponseEntity.ok().body(addressService.create(address, personToEdit));
		}
		return null;
	}

	@PutMapping(value ="/{id}/main-address/{addressId}")
	private ResponseEntity<Address> newMainAddress(@PathVariable long id, @PathVariable long addressId)
	{
		return ResponseEntity.ok().body(personService.updateMainAddress(id, addressId));
	}



	public boolean hasOnlyOneMainAddress(List<Address> addresses)
	{
		int count = 0;

		for (Address address : addresses)
		{
			if (address.isMainAddress())
			{
				count++;
			}
		}
		return count == 1;
	}

	public boolean isValidPersonInfo(Person person)
	{
		return person.getName() != null && !"".equals(person.getName()) && person.getBirthdate() != null;
	}

	public boolean isValidAddressInfo(Address address)
	{
		return (address.getCity() != null && !"".equals(address.getCity()) && address.getStreet() != null
			&& !"".equals(address.getStreet()) && address.getZipCode() != null && !"".equals(
			address.getZipCode()));
	}

}

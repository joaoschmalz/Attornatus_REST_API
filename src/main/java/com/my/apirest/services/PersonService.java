package com.my.apirest.services;

import com.my.apirest.models.Address;
import com.my.apirest.models.Person;
import com.my.apirest.repository.AddressRepository;
import com.my.apirest.repository.PersonRepository;
import com.my.apirest.services.exceptions.ObjectNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService
{
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressRepository addressRepository;

	public Person create(Person person)
	{
		return personRepository.save(person);
	}

	public Person findById(long id)
	{
		return personRepository.findById(id).orElseThrow(
			() -> new ObjectNotFoundException("Person not found with given identifier " + id));
	}

	public List<Person> findAll()
	{
		return personRepository.findAll();
	}

	public List<Address> findAdresses(long id)
	{
		return findById(id).getAddress();
	}

	public Person update(long id, Person person)
	{
		Person newPerson = findById(id);

		newPerson.setName(person.getName());
		newPerson.setBirthdate(person.getBirthdate());

		return personRepository.save(newPerson);
	}

	public void delete(Long id)
	{
		findById(id);
		personRepository.deleteById(id);
	}

	public Address updateMainAddress(long id, long addressId)
	{
		List<Address> addresses = findById(id).getAddress();

		updateActiveMainAddress(addresses);

		return newMainAddress(addresses, addressId);
	}

	public void updateActiveMainAddress(List<Address> addresses)
	{
		for (Address existingAddress : addresses)
		{
			if (existingAddress.isMainAddress())
			{
				existingAddress.setMainAddress(false);
				addressRepository.save(existingAddress);
			}
		}
	}

	public Address newMainAddress(List<Address> addresses, long addressId)
	{
		Address newMainAddress = null;

		for (Address address : addresses)
		{
			if (address.getId() == addressId)
			{
				address.setMainAddress(true);
				addressRepository.save(address);
				newMainAddress = address;
				break;
			}
		}
		return newMainAddress;
	}
}

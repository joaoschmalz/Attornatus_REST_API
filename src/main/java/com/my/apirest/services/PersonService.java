package com.my.apirest.services;

import com.my.apirest.models.Address;
import com.my.apirest.models.Person;
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
	private AddressService addressService;

	public Person create(Person person)
	{
		List<Address> addresses = person.getAddress();

		if (oneMainAddressOnly(addresses))
		{
			return null;
		}
		Person newPerson = personRepository.save(new Person(person.getName(), person.getBirthdate()));
		for (Address address : addresses)
		{
			addressService.create(address, newPerson);
		}
		return newPerson;
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
		if (oneMainAddressOnly(person.getAddress()) && !isValidPersonInfo(person))
		{
			return null;
		}
		Person newPerson = findById(id);

		newPerson.setName(person.getName());
		newPerson.setBirthdate(person.getBirthdate());

		return personRepository.save(newPerson);
	}

	public void delete(Long id)
	{
		Person person = findById(id);
		List<Address> addresses = person.getAddress();
		addressService.deleteAll(addresses);
		personRepository.deleteById(id);
	}

	public Address setNewMainAddress(long id, long addressId)
	{
		Person person = findById(id);
		turnActiveMainAddressFalse(person);

		return createNewMainAddress(person.getAddress(), addressId);
	}

	public void updateActiveMainAddress(long personId, Address address)
	{
		Person personToEdit = this.findById(personId);

		if (personToEdit != null && isValidAddressInfo(address))
		{
			turnActiveMainAddressFalse(personToEdit);
		}
	}

	public void turnActiveMainAddressFalse(Person person){
		for (Address existingAddress : person.getAddress())
		{
			if (existingAddress.isMainAddress())
			{
				existingAddress.setMainAddress(false);
				addressService.save(existingAddress);
			}
		}
	}

	public Address createNewMainAddress(List<Address> addresses, long addressId)
	{
		Address newMainAddress = null;

		for (Address address : addresses)
		{
			if (address.getId() == addressId)
			{
				address.setMainAddress(true);
				addressService.save(address);
				newMainAddress = address;
				break;
			}
		}
		return newMainAddress;
	}

	public boolean oneMainAddressOnly(List<Address> addresses)
	{
		int count = 0;

		for (Address address : addresses)
		{
			if (address.isMainAddress())
			{
				count++;
			}
		}
		return count != 1;
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

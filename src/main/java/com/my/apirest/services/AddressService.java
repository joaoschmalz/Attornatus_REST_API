package com.my.apirest.services;

import com.my.apirest.models.Address;
import com.my.apirest.models.Person;
import com.my.apirest.repository.AddressRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService
{
	@Autowired
	private AddressRepository repository;

	public Address create(Address address, Person person)
	{
		return repository.save(
			new Address(person, address.getStreet(), address.getZipCode(), address.getNumber(),
				address.getCity(), address.isMainAddress()));
	}

	public void save(Address address)
	{
		repository.save(address);
	}

	public void deleteAll(List<Address> addresses)
	{
		repository.deleteAll(addresses);
	}
}

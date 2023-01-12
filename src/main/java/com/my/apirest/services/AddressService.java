package com.my.apirest.services;

import com.my.apirest.models.Address;
import com.my.apirest.models.Person;
import com.my.apirest.repository.AddressRepository;
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

	public void updateMainAddress(Address address)
	{
		repository.save(address);
	}
}

package com.my.apirest.services;

import com.my.apirest.models.Address;
import com.my.apirest.models.Person;
import com.my.apirest.repository.AddressRepository;
import com.my.apirest.repository.PersonRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService
{
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private AddressRepository addressRepository;

	public void firstLoad()
	{
		Person p1 = new Person("João", new Date(95, Calendar.AUGUST, 15));
		Person p2 = new Person("Pedro", new Date(05, Calendar.JULY, 2));
		List<Person> people = personRepository.saveAll(Arrays.asList(p1, p2));

		Address a1 = new Address(people.get(0), "Francisco Nicodemus", "89222020", 480L, "Joinville", true);
		Address a2 = new Address(people.get(0), "Rua das Turmalinas", "89218070", 121, "Joinville", false);
		Address a3 = new Address(people.get(1), "Rua das Turmalinas", "89218070", 121, "Joinville", true);
		addressRepository.saveAll(Arrays.asList(a1, a2, a3));
	}
}

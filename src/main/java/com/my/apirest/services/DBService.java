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
		Person p1 = new Person("João", new GregorianCalendar(1995, Calendar.AUGUST, 15));
		Person p2 = new Person("José", new GregorianCalendar(2007, Calendar.JUNE, 2));

		List<Person> people = personRepository.saveAll(Arrays.asList(p1, p2));

		Address a1 = new Address(people.get(0), "Rua dos Alemães", "98202345", 304, "Joinville", true);
		Address a2 = new Address(people.get(0), "Rua das Brasileiros", "89234505", 123, "Joinville", false);
		Address a3 = new Address(people.get(1), "Rua das Turcos", "89205786", 567, "Joinville", true);
		addressRepository.saveAll(Arrays.asList(a1, a2, a3));
	}
}

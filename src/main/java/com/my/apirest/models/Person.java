package com.my.apirest.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
public class Person implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "NAME field is required !")
	private String name;

	@NotNull(message = "BIRTHDATE field is required !")
	private GregorianCalendar birthdate;

	@OneToMany(mappedBy = "person")
	@JsonManagedReference
	private final List<Address> address = new ArrayList<>();

	public Person()
	{
		super();
	}

	public Person(String name, GregorianCalendar birthdate)
	{
		this.name = name;
		this.birthdate = birthdate;
	}

	public long getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public GregorianCalendar getBirthdate()
	{
		return birthdate;
	}

	public void setBirthdate(GregorianCalendar birthdate)
	{
		this.birthdate = birthdate;
	}

	public List<Address> getAddress()
	{
		return address;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		Person person = (Person) o;
		return id == person.id;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}

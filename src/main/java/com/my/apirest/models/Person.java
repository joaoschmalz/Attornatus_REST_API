package com.my.apirest.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Person implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "NAME field is required !")
	private String name;

	@NotNull(message = "BIRTHDATE field is required !")
	private Date birthdate;

	@OneToMany(mappedBy = "person")
	@JsonManagedReference
	private List<Address> address = new ArrayList<Address>();

	public Person()
	{
		super();
	}

	public Person(String name, Date birthdate /*, List<Address> address*/)
	{
		this.name = name;
		this.birthdate = birthdate;
//		this.address = address;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getBirthdate()
	{
		return birthdate;
	}

	public void setBirthdate(Date birthdate)
	{
		this.birthdate = birthdate;
	}

	public List<Address> getAddress()
	{
		return address;
	}

	public void setAddress(List<Address> address)
	{
		this.address = address;
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

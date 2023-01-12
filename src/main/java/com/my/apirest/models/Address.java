package com.my.apirest.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Address
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JsonBackReference
	private Person person;
	@NotEmpty
	private String street;
	@NotEmpty
	private String zipCode;
	@NotNull
	private long number;
	@NotEmpty
	private String city;
	@NotNull
	private boolean mainAddress;

	public Address()
	{
		super();
	}

	public Address(Person person, String street, String zipCode, long number, String city, boolean mainAddress)
	{
		this.person = person;
		this.street = street;
		this.zipCode = zipCode;
		this.number = number;
		this.city = city;
		this.mainAddress = mainAddress;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Person getPerson()
	{
		return person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	public String getStreet()
	{
		return street;
	}

	public void setStreet(String street)
	{
		this.street = street;
	}

	public String getZipCode()
	{
		return zipCode;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	public long getNumber()
	{
		return number;
	}

	public void setNumber(long number)
	{
		this.number = number;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public boolean isMainAddress()
	{
		return mainAddress;
	}

	public void setMainAddress(boolean mainAddress)
	{
		this.mainAddress = mainAddress;
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
		Address address = (Address) o;
		return id == address.id;
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id);
	}
}

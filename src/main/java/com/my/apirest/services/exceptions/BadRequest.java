package com.my.apirest.services.exceptions;

public class BadRequest extends RuntimeException
{
	public BadRequest(String message, Throwable cause)
	{
		super(message, cause);
	}

	public BadRequest(String message)
	{
		super(message);
	}
}

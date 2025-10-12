package org.springboot.com.LibraryManagementSystem1.exception;

public class IdNotFound extends RuntimeException {

	String s="Id Not Found";
	public IdNotFound()
	{
		
	}
	
	public IdNotFound(String s)
	{
		this.s=s;
	}
	
	public String getMessage()
	{
		return s;
		
	}
}

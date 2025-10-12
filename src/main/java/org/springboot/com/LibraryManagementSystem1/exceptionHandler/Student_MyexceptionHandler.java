package org.springboot.com.LibraryManagementSystem1.exceptionHandler;

import org.springboot.com.LibraryManagementSystem1.util.ResponceStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Student_MyexceptionHandler {
	
	ResponceStructure<String> structure=new ResponceStructure<String>();

	@ExceptionHandler(org.springboot.com.LibraryManagementSystem1.exception.IdNotFound.class)
	public ResponseEntity<ResponceStructure<String>> IdNotFound(org.springboot.com.LibraryManagementSystem1.exception.IdNotFound found)
	{
		structure.setMsg("Data Not Found");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		structure.setData(found.getMessage());
		
		return new ResponseEntity<ResponceStructure<String>>(structure, HttpStatus.NOT_FOUND);
	}

}

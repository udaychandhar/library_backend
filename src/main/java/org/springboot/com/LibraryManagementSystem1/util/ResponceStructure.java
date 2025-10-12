package org.springboot.com.LibraryManagementSystem1.util;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponceStructure<T> {

	private String msg;
	private int statusCode;
	private T data;
	private LocalDateTime date;	
	
}

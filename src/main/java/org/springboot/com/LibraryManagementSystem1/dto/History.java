package org.springboot.com.LibraryManagementSystem1.dto;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class History {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private int id;
	private LocalDate issue_date;
	private String book_issue;
	private String book_name;
	private LocalDate last_date;
	
}

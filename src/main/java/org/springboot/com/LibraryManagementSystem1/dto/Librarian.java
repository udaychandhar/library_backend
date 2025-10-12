package org.springboot.com.LibraryManagementSystem1.dto;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Librarian {

	@Id
	 @GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "org.springboot.com.LibraryManagementSystem1.dto.CustomIdGeneratorLibrarian")
	@Column(length = 20)
	private String eid;
	private String  ename;
	private long mobile;
	
	
	
}

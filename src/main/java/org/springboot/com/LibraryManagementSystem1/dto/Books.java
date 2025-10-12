package org.springboot.com.LibraryManagementSystem1.dto;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Books {

	@Id
	@GeneratedValue(generator = "custom-id")
    @GenericGenerator(name = "custom-id", strategy = "org.springboot.com.LibraryManagementSystem1.dto.CustomIdGeneratorBook")
	@Column(length = 20)
	private String bid;
	private String bname;
	private String author;
	private int no_of_copies;
	private int available_copies;
	private double price;
	private String branch;
	
	@Lob
	@Column(columnDefinition = "longblob",length=999999999)
	private byte img[];
	
}

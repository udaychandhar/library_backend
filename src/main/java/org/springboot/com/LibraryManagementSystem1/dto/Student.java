package org.springboot.com.LibraryManagementSystem1.dto;

import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Student {

		@Id
	    @GeneratedValue(generator = "custom-id")
	    @GenericGenerator(name = "custom-id", strategy = "org.springboot.com.LibraryManagementSystem1.dto.CustomIdGeneratorStudent")
		@Column(length = 20)
		private String sid;
		private String sname;
		private String email;
		private long mobile;
		private String branch;
		private String gender;
		private int lmt;
		
		@OneToMany(cascade = CascadeType.ALL)
		private List<History> history;
		
}

package org.springboot.com.LibraryManagementSystem1.repositery;

import org.springboot.com.LibraryManagementSystem1.dto.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepositery extends JpaRepository<Books, String>{ 
	public Books findByBname(String name);
}

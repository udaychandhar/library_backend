package org.springboot.com.LibraryManagementSystem1.repositery;

import org.springboot.com.LibraryManagementSystem1.dto.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LIbrarianRepositery extends JpaRepository<Librarian, String>{

	
}

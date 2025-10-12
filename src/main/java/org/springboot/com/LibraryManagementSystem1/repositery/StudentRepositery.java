package org.springboot.com.LibraryManagementSystem1.repositery;

import org.springboot.com.LibraryManagementSystem1.dto.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositery extends JpaRepository<Student, String>{
	public Student findByMobile(long mobile);
}

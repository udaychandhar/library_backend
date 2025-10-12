package org.springboot.com.LibraryManagementSystem1.dao;


import java.util.List;
import java.util.Optional;

import org.springboot.com.LibraryManagementSystem1.dto.Student;
import org.springboot.com.LibraryManagementSystem1.repositery.BookRepositery;
import org.springboot.com.LibraryManagementSystem1.repositery.HistoryRepository;
import org.springboot.com.LibraryManagementSystem1.repositery.StudentRepositery;
import org.springboot.com.LibraryManagementSystem1.dto.Books;
import org.springboot.com.LibraryManagementSystem1.dto.History;
import org.springboot.com.LibraryManagementSystem1.dto.Librarian;
import org.springboot.com.LibraryManagementSystem1.repositery.LIbrarianRepositery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryDao {

	@Autowired
	private LIbrarianRepositery librarianRepositery;
	@Autowired
	private StudentRepositery studentRepositery;
	@Autowired
	private BookRepositery bookRepositery;
	@Autowired
	private HistoryRepository historyRepository;
	
	//Librarian Details
	public Librarian saveLibrarian(Librarian librarian)
	{
		return librarianRepositery.save(librarian);
	}
	
	public Librarian findLibrarian(String id)
	{
		 Optional<Librarian> librarian=librarianRepositery.findById(id);
		 if(librarian.isPresent()) {
			 return librarian.get();
		 }else {
			 return null;
		 }
	}
	
	//Student Details
	public Student saveStudent(Student student)
	{
		return studentRepositery.save(student);
	}
	
	public Student findStudent(String sid)
	{
		Optional<Student> st= studentRepositery.findById(sid);
		
		if(st.isPresent())
		{
			return st.get();
		}
		else
		{
			return null;
		}
	}
	
	
	//Book Details
	public Books saveBook(Books books)
	{
		return bookRepositery.save(books);
	}
	
	public Books findBook(String bid)
	{
		Optional<Books> book= bookRepositery.findById(bid);
		
		if(book.isPresent())
		{
			return book.get();
		}
		else
		{
			return null;
		}
	}
	public Student findStudentByMobile(long mobile)
	{
		Student student=studentRepositery.findByMobile(mobile);
		
		if(student !=null)
		{
			return student;
		}
		else
		{
			return null;
		}
	}
	
	public Books findByBookName(String name) {
		Books books=bookRepositery.findByBname(name);
		if(books!=null) {
			return books;
		}else {
			return null;
		}
	}
	
	public List<Books> findAllBooks(){
		return bookRepositery.findAll();
	}
	
	public Books deleteBook(String bid)
	{
		Optional<Books> book= bookRepositery.findById(bid);
		 if (book.isPresent()) {
		        bookRepositery.delete(book.get());
		        return book.get();  // return the deleted book if needed
		    } else {
		        return null;  // or optionally throw an exception for not found
		    }
	
	}
	
	public Student deleteStudent(String sid)
	{
		Optional<Student> student= studentRepositery.findById(sid);
		if(student.isPresent())
		{
			studentRepositery.delete(student.get());
			return student.get();
		}else {
			return null;
		}
	}
	
	public List<Student> findAllStudents()
	{
		return studentRepositery.findAll();
	}
	
	public List<History> findAllHistory(){
		return historyRepository.findAll();
	}
}

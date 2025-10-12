package org.springboot.com.LibraryManagementSystem1.controller;

import java.io.IOException;
import java.util.List;

import org.springboot.com.LibraryManagementSystem1.dto.Books;
import org.springboot.com.LibraryManagementSystem1.dto.History;
import org.springboot.com.LibraryManagementSystem1.dto.Librarian;
import org.springboot.com.LibraryManagementSystem1.dto.Student;
import org.springboot.com.LibraryManagementSystem1.service.Librarian_Service;
import org.springboot.com.LibraryManagementSystem1.util.ResponceStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*" ,methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE} )

public class LibraryController {

	@Autowired
	private Librarian_Service service;
	
	@PostMapping("/Librarian_up")
	public ResponseEntity<ResponceStructure<Librarian>> saveLibrarian(@RequestBody Librarian librarian){
		return service.UpdateLibrarian(librarian);
	}
	
	@PostMapping("/Student_reg")
	public ResponseEntity<ResponceStructure<Student>> Registration(@RequestBody Student student){
		return service.saveStudent(student);
	}
	
	
	@PostMapping("/Student_up")
	public ResponseEntity<ResponceStructure<Student>> updateStudent(@RequestBody Student student)
	{
		return service.updateStudent(student);
	}
	
	@GetMapping("/BookDetails/{bname}")
	public ResponseEntity<ResponceStructure<Books>> findBook(@PathVariable String bname )
	{
		return service.findBook(bname);
	}
	
	@PostMapping("/loginStudent/{id} & {mobile}")
	public ResponseEntity<ResponceStructure<Student>> loginStudent(@PathVariable String id, @PathVariable long mobile){
		return service.loginStudent(id, mobile);
	}
	
	@PostMapping("/loginLibrarian/{id} & {mobile}")
	public ResponseEntity<ResponceStructure<Librarian>> loginLibrarian(@PathVariable String id, @PathVariable long mobile){
		return service.loginLibrarian(id, mobile);
	}
	
	
	@PostMapping("/history_up/{sid}")
	public ResponseEntity<ResponceStructure<Student>> updateHistory(@PathVariable String sid , @RequestBody History history ){
		return service.updateHistory(sid, history);
	}
	
	
	
	@GetMapping("/all_books")
	public ResponseEntity<ResponceStructure<List<Books>>> findAllBooks(){
		return service.findAllBooks();
	}
	
	@DeleteMapping("/Book_Delete/{bid}")
	public ResponseEntity<ResponceStructure<Books>> deleteBook(@PathVariable String bid)
	{
		return service.deleteBook(bid);
	}
	
	@DeleteMapping("/Student_Delete/{sid}")
	public ResponseEntity<ResponceStructure<Student>> deleteStudent(@PathVariable String sid)
	{
		return service.deleteStudent(sid);
	}
	@GetMapping("/Student_All")
	public ResponseEntity<ResponceStructure<List<Student>>> findAllStudents()
	{
		return service.findAllStudents();
	}
	
	@PostMapping("/addBook_Image")
	public ResponseEntity<ResponceStructure<Books>> addBook_Image(@RequestPart Books books,@RequestPart(value = "image", required = false) MultipartFile file ) throws IOException
	{
		return service.addBook_Image(books, file);
	}
	
	@PostMapping("/EditBook_Image")
	public ResponseEntity<ResponceStructure<Books>> EditBook_Image(@RequestPart Books books,@RequestPart(value = "image", required = false) MultipartFile file ) throws IOException
	{
		return service.addBook_Image(books, file);
	}
	
	@PostMapping("/issue/{sid} && {bid}")
	public ResponseEntity<ResponceStructure<Student>> history(@PathVariable String sid,@PathVariable String bid ,@RequestBody History history){
			
		return service.issue(sid, bid, history);
	}
	
	@PostMapping("/returnbook/{sid} && {bid}")
	public ResponseEntity<ResponceStructure<Student>> returnBook(@PathVariable String sid,@PathVariable String bid,@RequestBody History history)
	{
		return service.returnBook(sid, bid, history);
	}
	
	@GetMapping("/student_history/{sid}")
	public ResponseEntity<ResponceStructure<List<History>>> stu_History(@PathVariable String sid){
		return service.history_all(sid);
	}
	
	@GetMapping("/all_history")
	public ResponseEntity<ResponceStructure<List<History>>> all_History(){
		return service.total_history();
	}
}


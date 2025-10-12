package org.springboot.com.LibraryManagementSystem1.service;

import java.io.IOException;
import java.util.List;

import org.springboot.com.LibraryManagementSystem1.dao.LibraryDao;
import org.springboot.com.LibraryManagementSystem1.dto.Books;
import org.springboot.com.LibraryManagementSystem1.dto.History;
import org.springboot.com.LibraryManagementSystem1.dto.Librarian;
import org.springboot.com.LibraryManagementSystem1.dto.Student;
import org.springboot.com.LibraryManagementSystem1.exception.IdNotFound;
import org.springboot.com.LibraryManagementSystem1.util.ResponceStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class Librarian_Service {

	@Autowired
	private LibraryDao dao;

	ResponceStructure<Librarian> strLib = new ResponceStructure<Librarian>();
	ResponceStructure<Student> strStu = new ResponceStructure<Student>();
	ResponceStructure<Books> strBook = new ResponceStructure<Books>();

	// Librarian

	public ResponseEntity<ResponceStructure<Librarian>> UpdateLibrarian(Librarian librarian) {
		strLib.setMsg("Librarian Data is Successfully updated");
		strLib.setStatusCode(HttpStatus.ACCEPTED.value());
		strLib.setData(dao.saveLibrarian(librarian));

		return new ResponseEntity<ResponceStructure<Librarian>>(strLib, HttpStatus.ACCEPTED);
	}

	// Student

	public ResponseEntity<ResponceStructure<Student>> saveStudent(Student student) {
		long mobile = student.getMobile();
		Student student2 = dao.findStudentByMobile(mobile);
		if (student2 == null) {
			strStu.setMsg("Student is Successfully Registered");
			strStu.setStatusCode(HttpStatus.OK.value());
			strStu.setData(dao.saveStudent(student));

			return new ResponseEntity<ResponceStructure<Student>>(strStu, HttpStatus.OK);
		} else {
			throw new IdNotFound("Mobile Number already Existed");
		}

	}

	public ResponseEntity<ResponceStructure<Student>> updateStudent(Student student) {
		long mobile = student.getMobile();
		Student student2 = dao.findStudentByMobile(mobile);
		if (student2 != null) {
			strStu.setMsg("Student is Successfully Registered");
			strStu.setStatusCode(HttpStatus.OK.value());
			strStu.setData(dao.saveStudent(student));

			return new ResponseEntity<ResponceStructure<Student>>(strStu, HttpStatus.OK);
		} else {
			throw new IdNotFound("Mobile Number not existed for updation");
		}

	}

	// Book

	public ResponseEntity<ResponceStructure<Books>> saveBook1(Books books) {
		strBook.setMsg("Librarian Data is Successfully updated");
		strBook.setStatusCode(HttpStatus.ACCEPTED.value());
		strBook.setData(dao.saveBook(books));

		return new ResponseEntity<ResponceStructure<Books>>(strBook, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponceStructure<Books>> findBook(String bName) {
		Books books = dao.findByBookName(bName);
		if (books != null) {
			strBook.setMsg("You are searching book is found");
			strBook.setStatusCode(HttpStatus.FOUND.value());
			strBook.setData(dao.findByBookName(bName));
			return new ResponseEntity<ResponceStructure<Books>>(strBook, HttpStatus.FOUND);
		} else {
			throw new IdNotFound("Book name is not found");
		}
	}

	public ResponseEntity<ResponceStructure<Librarian>> loginLibrarian(String id, long mobile) {
		Librarian librarian = dao.findLibrarian(id);
		if (librarian != null) {
			if (librarian.getMobile() == mobile) {
				strLib.setMsg("Librarian Login Successfully Done....!");
				strLib.setStatusCode(HttpStatus.OK.value());
				strLib.setData(librarian);

				return new ResponseEntity<ResponceStructure<Librarian>>(strLib, HttpStatus.OK);
			} else {
				throw new IdNotFound("Mobile Number is not Matched");
			}
		} else {
			throw new IdNotFound("Id Not Found");
		}
	}

	public ResponseEntity<ResponceStructure<Student>> loginStudent(String id, long mobile) {
		Student student = dao.findStudent(id);
		if (student != null) {
			if (student.getMobile() == mobile) {
				strStu.setMsg("Student Login Successfully Done....!");
				strStu.setStatusCode(HttpStatus.OK.value());
				strStu.setData(student);

				return new ResponseEntity<ResponceStructure<Student>>(strStu, HttpStatus.OK);
			} else {
				throw new IdNotFound("Mobile Number is not Matched");
			}
		} else {
			throw new IdNotFound("Id Not Found");
		}
	}

	public ResponseEntity<ResponceStructure<Books>> updateBook(Books books) {
		String id = books.getBid();
		Books book = dao.findBook(id);
		if (book != null) {
			strBook.setMsg("Book is updated ");
			strBook.setStatusCode(HttpStatus.OK.value());
			strBook.setData(dao.saveBook(books));

			return new ResponseEntity<ResponceStructure<Books>>(strBook, HttpStatus.OK);
		} else {
			throw new IdNotFound("Book is not exist");
		}
	}

	public ResponseEntity<ResponceStructure<Student>> updateHistory(String sid, History history) {
		Student student = dao.findStudent(sid);
		if (student != null) {
			List<History> list = student.getHistory();
			list.add(history);
			student.setHistory(list);

			strStu.setMsg("Student's Book history is updated");
			strStu.setStatusCode(HttpStatus.OK.value());
			strStu.setData(dao.saveStudent(student));

			return new ResponseEntity<ResponceStructure<Student>>(strStu, HttpStatus.OK);
		} else {
			throw new IdNotFound();
		}
	}

	

	public ResponseEntity<ResponceStructure<List<Books>>> findAllBooks() {
		ResponceStructure<List<Books>> response = new ResponceStructure<List<Books>>();
		List<Books> books = dao.findAllBooks();
		response.setMsg("All books are found");
		response.setStatusCode(HttpStatus.FOUND.value());
		response.setData(books);

		return new ResponseEntity<ResponceStructure<List<Books>>>(response, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponceStructure<Books>> deleteBook(String bid) {
		Books books = dao.findBook(bid);

		if (books != null) {
			strBook.setMsg("Book successfully Deleted....!");
			strBook.setStatusCode(HttpStatus.OK.value());
			strBook.setData(dao.deleteBook(bid));
			return new ResponseEntity<ResponceStructure<Books>>(strBook, HttpStatus.OK);
		} else {
			throw new IdNotFound();
		}

	}

	public ResponseEntity<ResponceStructure<Student>> deleteStudent(String sid) {
		Student student = dao.findStudent(sid);

		if (student != null) {
			strStu.setMsg("Student successfully Deleted....!");
			strStu.setStatusCode(HttpStatus.OK.value());
			strStu.setData(dao.deleteStudent(sid));
			return new ResponseEntity<ResponceStructure<Student>>(strStu, HttpStatus.OK);
		} else {
			throw new IdNotFound();
		}
	}

	public ResponseEntity<ResponceStructure<List<Student>>> findAllStudents() {
		ResponceStructure<List<Student>> response = new ResponceStructure<List<Student>>();
		List<Student> student = dao.findAllStudents();
		response.setMsg("All Student are found");
		response.setStatusCode(HttpStatus.FOUND.value());
		response.setData(student);

		return new ResponseEntity<ResponceStructure<List<Student>>>(response, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponceStructure<Books>> addBook_Image(Books books, MultipartFile file) throws IOException {
		if (file != null && !file.isEmpty()) {
			books.setImg(file.getBytes());
			strBook.setMsg("Book Details and Book image successpully Added....!");
			strBook.setStatusCode(HttpStatus.OK.value());
			strBook.setData(dao.saveBook(books));

			return new ResponseEntity<ResponceStructure<Books>>(strBook, HttpStatus.OK);
		} else {
			throw new IdNotFound("image is null");
		}

	}

	public ResponseEntity<ResponceStructure<Books>> EditBook_Image(Books books, MultipartFile file) throws IOException {
		String id = books.getBid();
		Books book = dao.findBook(id);
		if (book != null) {
			books.setImg(file.getBytes());
			strBook.setMsg("Book Details and Book image successpully Edited....!");
			strBook.setStatusCode(HttpStatus.OK.value());
			strBook.setData(dao.saveBook(books));

			return new ResponseEntity<ResponceStructure<Books>>(strBook, HttpStatus.OK);
		} else {
			throw new IdNotFound("image is null");
		}

	}

	public ResponseEntity<ResponceStructure<Student>> issue(String sid, String bid, History history) {
		Student student = dao.findStudent(sid);
		Books book = dao.findBook(bid);
		boolean check = false;

		if (student != null && book != null) {
			List<History> historyList = student.getHistory();
			if (historyList.isEmpty()) {
				check = true;
			} else {
				for (History history2 : historyList) {
					if (history2.getBook_issue().equals(bid) && history2.getLast_date() == null) {
						check = false; // book already issued and not returned
						break;
					}

				}

			}

			if (check) {
				if (student.getLmt() > 0) {
					if (book.getAvailable_copies() != 0) {
						history.setBook_issue(book.getBid());
						history.setBook_name(book.getBname());

						List<History> list1 = student.getHistory();
						list1.add(history);
						student.setHistory(list1);

						student.setLmt(student.getLmt() - 1);
						book.setAvailable_copies(book.getAvailable_copies() - 1);
						updateBook(book);

						strStu.setMsg("Book is issued");
						strStu.setStatusCode(HttpStatus.OK.value());
						strStu.setData(dao.saveStudent(student));

						return new ResponseEntity<ResponceStructure<Student>>(strStu, HttpStatus.OK);

					} else {
						throw new IdNotFound("Book not available");
					}
				} else {
					throw new IdNotFound("Student limit is exceeded");
				}
			} else {
				throw new IdNotFound("Book is already issued");
			}

		} else {
			throw new IdNotFound("Student Id not found OR  Book id not found");
		}

	}

	public ResponseEntity<ResponceStructure<Student>> returnBook(String sid, String bid, History history) {
		Student student = dao.findStudent(sid);
		Books book = dao.findBook(bid);
		boolean check = false;

		if (student != null && book != null) {
			List<History> historyList = student.getHistory();
			if (historyList.isEmpty()) {
				check = false;
			} else {
				for (History history2 : historyList) {
					if (history2.getBook_issue().equals(bid) && history2.getLast_date() == null) {
						check = true;
						history2.setLast_date(history.getLast_date());
						
						
						student.setHistory(historyList);
						student.setLmt(student.getLmt() + 1);
						book.setAvailable_copies(book.getAvailable_copies() + 1);
						updateBook(book);
					}

				}

			}

			if (check) {

				strStu.setMsg("Book is returned");
				strStu.setStatusCode(HttpStatus.OK.value());
				strStu.setData(dao.saveStudent(student));

				return new ResponseEntity<ResponceStructure<Student>>(strStu, HttpStatus.OK);

			} else {
				throw new IdNotFound("Book is not issued");
			}

		} else {
			throw new IdNotFound("Student Id not found OR  Book id not found");
		}
	}
	
	public ResponseEntity<ResponceStructure<List<History>>> history_all(String sid)
	{
		ResponceStructure<List<History>> structure=new ResponceStructure<List<History>>();
		Student student=dao.findStudent(sid);
		if(student != null) {
			List<History> histories=student.getHistory();
			
			structure.setMsg("Your history is successfully fetched");
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setData(histories);
			
			return new ResponseEntity<ResponceStructure<List<History>>> (structure, HttpStatus.OK);
			
		}else {
			throw new IdNotFound("Student Id not found");
		}
		
	}
	
	public ResponseEntity<ResponceStructure<List<History>>> total_history(){
		ResponceStructure<List<History>> structure=new ResponceStructure<List<History>>();
		 
		structure.setMsg("All histroy details are fetched");
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setData(dao.findAllHistory());
		
		return new ResponseEntity<ResponceStructure<List<History>>> (structure, HttpStatus.OK);
		
	}
	
}

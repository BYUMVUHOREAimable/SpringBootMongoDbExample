package com.javatalent.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javatalent.entity.Book;
import com.javatalent.repo.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {
	
	@Autowired
	BookRepository bookRepo;
	
	//Fetching the record (get())
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> books = new ArrayList<Book>();
		bookRepo.findAll().forEach(books::add);
		return new ResponseEntity<>(books,HttpStatus.OK);
	
	}
	
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable("id") String id){
		Optional<Book> book = bookRepo.findById(id);
		return new ResponseEntity<>(book.get(),HttpStatus.OK);
	
	}
	
	@PostMapping("/books")
	public ResponseEntity<String> saveBook(@RequestBody Book book){
		try {
		bookRepo.save(book);
		return new ResponseEntity<String>("Book Saved to the DB",HttpStatus.OK);
	}catch(Exception e) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	}
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable("id") String id, @RequestBody Book book) {
	    Optional<Book> opt = bookRepo.findById(id);
	    if(opt.isPresent()) {
	        Book existingBook = opt.get();
	        existingBook.setAuthorName(book.getAuthorName());
	        existingBook.setBookId(book.getBookId());
	        existingBook.setBookName(book.getBookName());
	        existingBook.setPrice(book.getPrice());
	        
	        // Save the updated book
	        Book updatedBook = bookRepo.save(existingBook);
	        
	        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
	    } else {
	        // If the book with the specified id is not found
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

		
		@DeleteMapping("/books/{id}")
		public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") String id){
			try {
				bookRepo.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				
			}
		}
			
		@DeleteMapping("/books")
		public ResponseEntity<HttpStatus> deleteAllBooks() {
		    try {
		        bookRepo.deleteAll();
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    } catch(Exception e) {
		        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		}
		
	}

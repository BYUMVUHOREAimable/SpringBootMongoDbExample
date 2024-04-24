package com.javatalent.runner;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javatalent.entity.Book;
import com.javatalent.repo.BookRepository;

@Component
public class FindTestRunner implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
       //find All
    	List<Book> bookList = bookRepository.findAll();
    	System.out.println("With findAll Method: ");
    	bookList.forEach(System.out::println);
    	
    	bookRepository.deleteAll(bookList);
    	
    	//findById
    	Optional<Book> opt =  bookRepository.findById("6628b912b903eb4dcfecbe2c");
    	System.out.println("With findById Method: ");
    	if(opt.isPresent()) {
    	Book book = opt.get();
    	System.out.println("This is the book details: " + book);
    } else {
    	System.out.println("Book not found with the given ID");

    }
    	//delete Operation
    	bookRepository.deleteById("6628b912b903eb4dcfecbe2c");
    	
    }
}

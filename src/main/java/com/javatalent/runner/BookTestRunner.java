package com.javatalent.runner;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.javatalent.entity.Book;
import com.javatalent.repo.BookRepository;

@Component
public class BookTestRunner implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        // Removing old data from the DB
        bookRepository.deleteAll();

        // Saving the books into the DB
        bookRepository.saveAll(Arrays.asList(
            new Book(101, "Core Java", "Kathy", 908.20),
            new Book(102, "Advanced Java", "John", 2020.00),
            new Book(103, "Hibernate", "Gavin", 1002.20),
            new Book(104, "MongoDB", "Shakuntala Gupta", 1500.00)
        ));

        //System.out.println("Data Saved to MongoDB Successfully!");

        // Update ID(PK) manually (It is allowed): It will create one new record!
        bookRepository.save(new Book(104, "MongoDB", "Gupta", 964.50));
        
        //Inserting the record using insert method
        bookRepository.insert(new Book(501,"Microservices","Ak Boss",1020.20));

        // Printing all books to the console
        List<Book> bookList = bookRepository.findAll();
        bookList.forEach(System.out::println);
    }
}

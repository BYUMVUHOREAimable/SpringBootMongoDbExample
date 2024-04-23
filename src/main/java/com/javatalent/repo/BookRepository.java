package com.javatalent.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javatalent.entity.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}

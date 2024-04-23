package com.javatalent.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Book {

    @Id
    private String id;

    @NonNull
    private Integer bookId;

    @NonNull
    private String bookName;

    @NonNull
    private String authorName;

    @NonNull
    private Double price;
    
 // Constructor matching the fields
    public Book(Integer bookId, String bookName, String authorName, Double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorName = authorName;
        this.price = price;
    }

    // Override toString() to provide meaningful representation
    @Override
    public String toString() {
        return String.format(
            "Book[id='%s', bookId=%d, bookName='%s', authorName='%s', price=%.2f]",
            id, bookId, bookName, authorName, price
        );
    }
}

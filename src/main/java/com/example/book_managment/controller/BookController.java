package com.example.book_managment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import java.util.List;
import com.example.book_managment.model.Book;
import com.example.book_managment.repository.BookRepository;

@Controller
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @QueryMapping
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return bookRepository.findById(id).orElse(null);
    }

}

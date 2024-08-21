package com.example.book_managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_managment.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
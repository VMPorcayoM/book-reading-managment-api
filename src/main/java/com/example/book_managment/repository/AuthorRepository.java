package com.example.book_managment.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_managment.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
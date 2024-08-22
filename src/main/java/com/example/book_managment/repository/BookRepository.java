package com.example.book_managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.book_managment.model.Author;
import com.example.book_managment.model.Book;
import com.example.book_managment.model.Genre;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE LOWER(b.title) = LOWER(:title) AND b.author = :author AND b.genre = :genre")
    Book findByTitleAndAuthorAndGenre(
        @Param("title") String title, 
        @Param("author") Author author, 
        @Param("genre") Genre genre);
}
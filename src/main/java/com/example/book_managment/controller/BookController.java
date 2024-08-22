package com.example.book_managment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import com.example.book_managment.model.Author;
import com.example.book_managment.model.Book;
import com.example.book_managment.model.Genre;
import com.example.book_managment.repository.AuthorRepository;
import com.example.book_managment.repository.BookRepository;
import com.example.book_managment.repository.GenreRepository;

import jakarta.transaction.Transactional;

@Controller
public class BookController {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @QueryMapping
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @QueryMapping
    public Book bookById(@Argument Long id) {
        return bookRepository.findById(id).orElse(null);
    }
    @Transactional
    @MutationMapping
    public Book createOrUpdateBook(@Argument Book book) {
        // Buscar o crear el autor
        Author author = authorRepository.findByNameIgnoreCase(book.getAuthor().getName())
            .orElseGet(() -> new Author(null, book.getAuthor().getName(), book.getAuthor().getBiography()));

        // Buscar o crear el género
        Genre genre = genreRepository.findByNameIgnoreCase(book.getGenre().getName())
            .orElseGet(() -> new Genre(null, book.getGenre().getName()));

        // Guardar el autor y el género si son nuevos
        if (author.getId() == null) {
            author = authorRepository.save(author);
        }
        if (genre.getId() == null) {
            genre = genreRepository.save(genre);
        }
        LocalDate publicationDate;
        try {
            publicationDate = LocalDate.parse(book.getPublicationDate().toString(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format, expected yyyy-MM-dd");
        }
        // Buscar si el libro ya existe
        Book existingBook = bookRepository.findByTitleAndAuthorAndGenre(
            book.getTitle(), author, genre);

        if (existingBook != null) {
            // El libro ya existe, retornar el libro existente
            return existingBook;
        }
        // Crear o actualizar el libro
        Book newBook = new Book();
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(author);
        newBook.setGenre(genre);
        newBook.setPublicationDate(publicationDate);

        return bookRepository.save(newBook);
    }
}

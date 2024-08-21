package com.example.book_managment.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
     // Relación many-to-one: muchos libros pueden pertenecer a un solo autor
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
    @Column(nullable = false)
    private String publication_date;
}
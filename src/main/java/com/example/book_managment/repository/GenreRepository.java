package com.example.book_managment.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.book_managment.model.Genre;
public interface GenreRepository extends JpaRepository<Genre, Long> {
   @Query("SELECT g FROM Genre g WHERE LOWER(g.name) = LOWER(:name)")
    Optional<Genre> findByNameIgnoreCase(@Param("name") String name);
}
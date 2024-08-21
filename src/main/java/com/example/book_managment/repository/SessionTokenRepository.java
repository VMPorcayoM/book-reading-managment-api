package com.example.book_managment.repository;
import com.example.book_managment.model.SessionToken;
import com.example.book_managment.model.User;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionTokenRepository extends JpaRepository<SessionToken, Long>{
    Optional<SessionToken> findByUserAndCreatedAt(User user, LocalDateTime created_at);
}

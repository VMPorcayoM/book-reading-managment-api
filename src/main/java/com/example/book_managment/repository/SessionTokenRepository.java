package com.example.book_managment.repository;

import com.example.book_managment.model.SessionToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionTokenRepository extends JpaRepository<SessionToken, Long>{
    Optional<SessionToken> findFirstByUser_IdOrderByCreatedAtDesc(Long userId);

}

package com.example.book_managment.controller;

import com.example.book_managment.exception.AuthenticationException;
import com.example.book_managment.model.SessionToken;
import com.example.book_managment.model.User;
import com.example.book_managment.repository.SessionTokenRepository;
import com.example.book_managment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SessionTokenRepository sessionTokenRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @QueryMapping
    public List<User> allUsers() {
        return userRepository.findAll();
    }
    @MutationMapping
    public User createUser(@Argument User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    @QueryMapping
    public User userById(@Argument Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    @MutationMapping
    public SessionToken login(@Argument String username, @Argument String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid username or password");
        }

        // Buscar un token válido existente
        Optional<SessionToken> existingToken = sessionTokenRepository.findFirstByUser_IdOrderByCreatedAtDesc(user.getId());
        if (existingToken.isPresent() && existingToken.get().getExpiresAt().isAfter(LocalDateTime.now())) {
            return existingToken.get(); // Retornar el token existente si es válido
        }

        // Generar nuevo token y guardar en la base de datos
        SessionToken sessionToken = new SessionToken();
        sessionToken.setUser(user);
        sessionToken.setToken(UUID.randomUUID().toString());
        sessionToken.setCreatedAt(LocalDateTime.now());
        sessionToken.setExpiresAt(LocalDateTime.now().plus(1, ChronoUnit.DAYS));
        return sessionTokenRepository.save(sessionToken);
    }

}

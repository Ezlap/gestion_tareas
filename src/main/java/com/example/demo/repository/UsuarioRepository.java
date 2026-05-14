package com.example.demo.repository;

import com.example.demo.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<usuario, Long> {
    Optional<usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}

package com.example.demo.repository;

import com.example.demo.model.tarea;
import com.example.demo.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TareaRepository extends JpaRepository<tarea, Long> {
    List<tarea> findByUsuario(usuario usuario);
}

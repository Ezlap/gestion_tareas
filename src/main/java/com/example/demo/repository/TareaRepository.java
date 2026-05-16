package com.example.demo.repository;

import com.example.demo.model.Tarea;
import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByUsuario(Usuario usuario);
}

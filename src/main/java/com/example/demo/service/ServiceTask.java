package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.model.Tarea;
import com.example.demo.model.Usuario;
import com.example.demo.repository.TareaRepository;
import com.example.demo.repository.UsuarioRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceTask {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TareaRepository tareaRepository;

private TaskResponse toResponse(Tarea tarea) {
    return new TaskResponse(
        tarea.getId(),
        tarea.getTitulo(),
        tarea.getDescripcion(),
        tarea.getEstado(),
        tarea.getFechaLimite(),
        tarea.getUsuario().getNombre(),
        tarea.getUsuario().getEmail()
    );
}

public TaskResponse createTask(TaskRequest request, String email) throws Exception {
    Usuario usuario = usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new Exception("Usuario no encontrado"));

    Tarea tarea = new Tarea();
    tarea.setTitulo(request.getTitulo());
    tarea.setDescripcion(request.getDescripcion());
    tarea.setEstado(request.getEstado() != null ? request.getEstado() : "PENDIENTE");
    tarea.setFechaLimite(request.getFechaLimite());
    tarea.setUsuario(usuario);

    return toResponse(tareaRepository.save(tarea));
    }

public List<TaskResponse> listarTareas(String email) {
    Usuario usuario = usuarioRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    return tareaRepository.findByUsuario(usuario)
        .stream()
        .map(this::toResponse)
        .collect(Collectors.toList());
    }

public TaskResponse actualizarTarea(Long id, TaskRequest request, String email) {
    Tarea tarea = tareaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Tarea no encontrado"));

    if (!tarea.getUsuario().getEmail().equals(email)) {
        throw new RuntimeException("No tienes permiso para modificar esta tarea");
    }

    tarea.setTitulo(request.getTitulo());
    tarea.setDescripcion(request.getDescripcion());
    tarea.setEstado(request.getEstado() != null ? request.getEstado() : tarea.getEstado());
    tarea.setFechaLimite(request.getFechaLimite());

        return toResponse(tareaRepository.save(tarea));
    }

public void deleteTaks(Long id, String email) {
    Tarea tarea = tareaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Tarea no encontrda"));

    if (!tarea.getUsuario().getEmail().equals(email)) {
        throw new RuntimeException("No tienes permitido eliminar esta tarea");
    }
        tareaRepository.delete(tarea);
    } 
}

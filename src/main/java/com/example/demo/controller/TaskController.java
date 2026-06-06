package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.service.ServiceTask;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {
    
    @Autowired
    private ServiceTask serviceTask;

    @GetMapping("/list")
    public ResponseEntity<?> list(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            String emailUsuario = userDetails.getUsername();
            return ResponseEntity.ok(serviceTask.listarTareas(emailUsuario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al listar tareas");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createTask(@Valid @RequestBody TaskRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String emailUsuario = userDetails.getUsername();
            serviceTask.createTask(request, emailUsuario);
            return ResponseEntity.ok("Tarea Creada correctamente");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Error al registrar");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(
        @PathVariable Long id,
        @RequestBody TaskRequest request,
        @AuthenticationPrincipal UserDetails userDetails) {
    try {
        String emailUsuario = userDetails.getUsername();
        TaskResponse tareaActualizada = serviceTask.actualizarTarea(id, request, emailUsuario);
        return ResponseEntity.ok(tareaActualizada);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar la tarea");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails userDetails) {
        try {
            String emailUsuario = userDetails.getUsername();
            serviceTask.deleteTaks(id, emailUsuario);
            return ResponseEntity.ok("Tarea eliminada correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar la tarea");
        }
    }
}

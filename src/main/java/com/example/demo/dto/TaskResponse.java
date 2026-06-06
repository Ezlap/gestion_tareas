package com.example.demo.dto;

import java.time.LocalDate;

public class TaskResponse {
    
    private Long id;
    private String titulo;
    private String descripcion;
    private String estado;
    private LocalDate fechaLimite; 
    private String usuarioNombre;
    private String usuarioEmail;

    public TaskResponse(Long id, String titulo, String descripcion, 
                        String estado, LocalDate fechaLimite,
                        String usuarioNombre, String usuarioEmail) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaLimite = fechaLimite;
        this.usuarioNombre = usuarioNombre;
        this.usuarioEmail = usuarioEmail;
    }

    //Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getEstado() { return estado; }
    public LocalDate getFechaLimite() { return fechaLimite; }
    public String getUsuarioNombre() { return usuarioNombre; }
    public String getUsuarioEmail() { return usuarioEmail; }
}

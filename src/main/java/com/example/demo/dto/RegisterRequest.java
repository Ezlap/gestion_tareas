package com.example.demo.dto;
import jakarta.validation.constraints.*;

public class RegisterRequest {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Email inválido")
    @NotBlank(message = "El email es obligatorio")
    private String email;

    @Size(min = 6, message = "Mínimo 6 caracteres")
    private String password;

    //Constructor
    public RegisterRequest(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    //Getters and Setters
    public String getNombre() {return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

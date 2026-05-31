package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.ServiceRegister;
import com.example.demo.service.ServiceLogin;

import jakarta.validation.Valid;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private ServiceRegister serviceRegister; 

    @Autowired
    private ServiceLogin serviceLogin;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        try {
            serviceRegister.Register(request);
            return ResponseEntity.ok("Usuario registrado exitosamente");
        }   catch(Exception e) {
                return ResponseEntity.badRequest().body("Error al registrar:" + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest request) {
        try {
            String token = serviceLogin.Login(request);
            return ResponseEntity.ok(token);
        }  catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales Incorrectas");
        }
    }
}

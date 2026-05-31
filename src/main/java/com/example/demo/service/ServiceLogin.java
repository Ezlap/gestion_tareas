package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.security.JwtUtil;

@Service
public class ServiceLogin {
    
    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String Login(LoginRequest request) throws Exception {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new Exception("El correo electrónico no existe"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new Exception("Contraseña Incorrecta");
        }

        String token = jwtUtil.generateToken(usuario.getEmail());

        return token;
    }
}

package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class ServiceRegister {
    @Autowired
    private UsuarioRepository usuarioRepository;

    //Encrypt Password
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public void Register(RegisterRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));

        usuarioRepository.save(usuario);
        
    }
}

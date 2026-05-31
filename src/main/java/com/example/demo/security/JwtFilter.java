package com.example.demo.security;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.User;
import com.example.demo.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override 
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Get the Authorization header
        String header = request.getHeader("Authorization");

        // Check if it exists and starts with "Bearer "
        if (header != null && header.startsWith("Bearer ")) {

            // Extract the token (remove "Bearer ")
            String token = header.substring(7);

            // Validates the token
            if (jwtUtil.validateToken(token)) {

                // Extract the email from the token
                String email = jwtUtil.extractEmail(token);

                // Verify that the user exists in the database
                boolean exists = usuarioRepository.existsByEmail(email);

                if (exists) {
                    // Create authentication object and set it in security context
                    UserDetails userDetails = new User(email, "", new ArrayList<>());
                    UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                    );
                SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        // Proceed with the request filter chain
        filterChain.doFilter(request, response);
    }
}

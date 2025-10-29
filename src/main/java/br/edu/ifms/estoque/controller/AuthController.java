/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.AuthenticationRequest;
import br.edu.ifms.estoque.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.model.Usuario;
import br.edu.ifms.estoque.service.JwtUtil;
import br.edu.ifms.estoque.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author 1513003
 */
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String registerUser(
            @RequestBody UsuarioRegisterRequest user
    ) {
        // Encode the user's password
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        // Save the user to the database
        usuarioService.save(user);
        return "Usuário registrado com sucesso";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        final Usuario usuario = usuarioService
                .loadUserByUsername(authenticationRequest
                        .getUsername());
        final String jwt = jwtUtil.generateToken(usuario);
        return jwt;
    }

}

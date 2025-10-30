/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.controller;

import br.edu.ifms.estoque.dto.AuthenticationRequest;
import br.edu.ifms.estoque.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 1513003
 */
@RestController
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/register")
    public String registerUser(
            @RequestBody UsuarioRegisterRequest user
    ) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestBody AuthenticationRequest authenticationRequest
    ) throws Exception {
        return authService.autenticar(authenticationRequest);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.AuthenticationRequest;
import br.edu.ifms.estoque.dto.UsuarioRegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Autenticar na aplicacao.
     * @param authentication
     * @return 
     */
    public String autenticar(Authentication authentication) {
        try {
            var user = usuarioService.loadUserByUsername(authentication.getName());
            var token = jwtUtil.generateToken(user);
            return token;
        } catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Usuário ou senha Inválidos!");
        }
    }

    public String autenticar(AuthenticationRequest authRequest) {
        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            return autenticar(authentication);
        } catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Usuário ou senha Inválidos!");
        }
    }

    public String autenticar(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationRequest authRequest) {

        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            return autenticar(authentication);
        } catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Usuário ou senha Inválidos!");
        }
    }
    
    public String register(UsuarioRegisterRequest user) {
        // Encode the user's password
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        // Save the user to the database
        usuarioService.save(user);
        return "Usuário registrado com sucesso";
    }

}
